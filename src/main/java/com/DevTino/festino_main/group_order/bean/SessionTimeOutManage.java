package com.DevTino.festino_main.group_order.bean;

import com.DevTino.festino_main.group_order.domain.DTO.OrderMessageDTO;
import com.DevTino.festino_main.group_order.domain.DTO.TimeInfo;
import com.DevTino.festino_main.group_order.domain.ENUM.TopicMessageType;
import com.DevTino.festino_main.group_order.domain.GroupOrderDAO;
import com.DevTino.festino_main.group_order.repository.GroupOrderRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
public class SessionTimeOutManage {

    private final GroupOrderRepositoryJPA groupOrderRepositoryJPA;
    private final SimpMessagingTemplate messagingTemplate;
    private final TaskScheduler taskScheduler;

    // 세션 ID를 키로 하여 각종 태스크를 저장
    private final Map<String, ScheduledFuture<?>> warningTasks = new ConcurrentHashMap<>();
    private final Map<String, ScheduledFuture<?>> endTasks = new ConcurrentHashMap<>();
    private final Map<String, ScheduledFuture<?>> timeUpdateTasks = new ConcurrentHashMap<>(); // 시간 업데이트 태스크 추가

    private static final int SESSION_TIMEOUT_MINUTES = 10; // 세션 타임아웃 10분
    private static final int WARNING_BEFORE_EXPIRY_MINUTES = 1; // 경고는 만료 1분 전에
    private static final int TIME_UPDATE_INTERVAL_SECONDS = 60; // 시간 업데이트 간격 (1분)

    @Autowired
    public SessionTimeOutManage(GroupOrderRepositoryJPA groupOrderRepositoryJPA, SimpMessagingTemplate messagingTemplate, TaskScheduler taskScheduler) {
        this.groupOrderRepositoryJPA = groupOrderRepositoryJPA;
        this.messagingTemplate = messagingTemplate;
        this.taskScheduler = taskScheduler;
    }

    // 새 세션이 생성될 때 호출
    @Transactional
    public void scheduleSessionTimeout(UUID boothId, Integer tableNum) {
        String sessionId = boothId + ":" + tableNum;

        // 기존 예약 태스크가 있으면 취소
        cancelExistingTasks(sessionId);

        // 세션 생성 시간 (지금) + 지정된 시간으로 만료 시간 계산
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryTime = now.plusMinutes(SESSION_TIMEOUT_MINUTES);
        LocalDateTime warningTime = now.plusMinutes(SESSION_TIMEOUT_MINUTES - WARNING_BEFORE_EXPIRY_MINUTES);

        // DB의 세션 정보 업데이트
        GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId).orElse(null);
        if (session != null) {
            session.setStartTime(now);
            session.setExpiryTime(expiryTime);
            groupOrderRepositoryJPA.save(session);

            // 경고 메시지 태스크 예약
            Instant warningInstant = warningTime.atZone(ZoneId.systemDefault()).toInstant();
            ScheduledFuture<?> warningTask = taskScheduler.schedule(
                    () -> sendPreSessionEndMessage(session),
                    warningInstant
            );
            warningTasks.put(sessionId, warningTask);

            // 종료 메시지 태스크 예약
            Instant expiryInstant = expiryTime.atZone(ZoneId.systemDefault()).toInstant();
            ScheduledFuture<?> endTask = taskScheduler.schedule(
                    () -> handleSessionExpiry(sessionId),
                    expiryInstant
            );
            endTasks.put(sessionId, endTask);

            // 시간 업데이트 태스크 추가 - 1분마다 실행
            scheduleTimeUpdateTask(session);

            // 첫 번째 시간 업데이트 메시지 즉시 전송
            sendTimeUpdateMessage(session);
        }
    }

    // 시간 업데이트 태스크 스케줄링
    private void scheduleTimeUpdateTask(GroupOrderDAO session) {
        String sessionId = session.getId();

        // 10초 주기로 반복 실행되는 태스크 생성
        ScheduledFuture<?> timeUpdateTask = taskScheduler.scheduleAtFixedRate(
                () -> {
                    // 세션이 아직 존재하는지 확인
                    GroupOrderDAO currentSession = groupOrderRepositoryJPA.findById(sessionId).orElse(null);
                    if (currentSession != null) {
                        sendTimeUpdateMessage(currentSession);
                    } else {
                        // 세션이 삭제된 경우, 이 태스크도 취소
                        ScheduledFuture<?> task = timeUpdateTasks.remove(sessionId);
                        if (task != null) {
                            task.cancel(false);
                        }
                    }
                },
                Duration.ofSeconds(TIME_UPDATE_INTERVAL_SECONDS)
        );

        timeUpdateTasks.put(sessionId, timeUpdateTask);
    }


    // 시간 업데이트 메시지 전송
    @Transactional
    private void sendTimeUpdateMessage(GroupOrderDAO session) {
        int remainingMinutes = getRemainingMinutes(session.getId());


        // TimeInfo DTO 생성
        TimeInfo timeInfo = TimeInfo.builder()
                .remainingMinutes(remainingMinutes)
                .build();

        // 메시지 생성
        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(TopicMessageType.TIMEUPDATE.name())
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .payload(timeInfo)
                .build();

        // 모든 구독자에게 메시지 전송
        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        messagingTemplate.convertAndSend(destination, message);
    }

    // 세션 만료 처리
    @Transactional
    public void handleSessionExpiry(String sessionId) {
        GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId).orElse(null);
        if (session != null) {
            // 세션 종료 메시지 전송
            sendSessionEndMessage(session);

            // 세션 삭제
            groupOrderRepositoryJPA.delete(session);
        }

        // 관련 태스크 정리
        cancelExistingTasks(sessionId);
    }

    // 기존 예약 태스크 취소
    private void cancelExistingTasks(String sessionId) {
        ScheduledFuture<?> warningTask = warningTasks.remove(sessionId);
        if (warningTask != null) {
            warningTask.cancel(false);
        }

        ScheduledFuture<?> endTask = endTasks.remove(sessionId);
        if (endTask != null) {
            endTask.cancel(false);
        }

        ScheduledFuture<?> timeUpdateTask = timeUpdateTasks.remove(sessionId);
        if (timeUpdateTask != null) {
            timeUpdateTask.cancel(false);
        }
    }

    // 세션 종료 전 경고 메시지 전송
    private void sendPreSessionEndMessage(GroupOrderDAO session) {
        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(TopicMessageType.PRESESSIONEND.name())
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .build();

        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        messagingTemplate.convertAndSend(destination, message);
    }

    // 세션 종료 메시지 전송
    private void sendSessionEndMessage(GroupOrderDAO session) {
        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(TopicMessageType.SESSIONEND.name())
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .build();

        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        messagingTemplate.convertAndSend(destination, message);
    }

    // 세션 남은 시간 조회 (분 단위, 더 정확한 계산)
    public int getRemainingMinutes(String sessionId) {
        GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId).orElse(null);
        if (session != null && session.getExpiryTime() != null) {
            LocalDateTime now = LocalDateTime.now();
            // 남은 시간 계산 - 초 단위로 계산 후 60으로 나누어 분으로 변환
            long remainingSeconds = Duration.between(now, session.getExpiryTime()).getSeconds();
            return (int) Math.max(0, (remainingSeconds + 59) / 60); // 올림 처리
        }
        return 0;
    }
}