package com.DevTino.festino_main.group_order.bean;

import com.DevTino.festino_main.DateTimeUtils;
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
        try {
            String sessionId = boothId + ":" + tableNum;

            // 기존 태스크가 있는지 확인
            boolean hasExistingTasks = warningTasks.containsKey(sessionId) ||
                    endTasks.containsKey(sessionId) ||
                    timeUpdateTasks.containsKey(sessionId);

            // 이미 태스크가 있으면(세션이 이미 설정되어 있으면) 아무것도 하지 않음
            if (hasExistingTasks) {
                return;
            }

            // 세션 생성 시간 (지금) + 지정된 시간으로 만료 시간 계산
            LocalDateTime now = DateTimeUtils.nowZone();
            LocalDateTime expiryTime = now.plusMinutes(SESSION_TIMEOUT_MINUTES);
            LocalDateTime warningTime = now.plusMinutes(SESSION_TIMEOUT_MINUTES - WARNING_BEFORE_EXPIRY_MINUTES);

            // DB의 세션 정보 업데이트
            GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Order session not found: " + sessionId));

            // 세션 만료 시간 설정
            session.setExpiryTime(expiryTime);
            groupOrderRepositoryJPA.save(session);
            groupOrderRepositoryJPA.flush();

//            // 경고 메시지 태스크 예약
//            schedulePreSessionEndTask(session, warningTime);
//
//            // 종료 메시지 태스크 예약
//            scheduleSessionEndTask(session, expiryTime);

            // 시간 업데이트 태스크 추가 - 1분마다 실행
            scheduleTimeUpdateTask(session);

            // 첫 번째 시간 업데이트 메시지 즉시 전송
            sendTimeUpdateMessage(session);


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 시간 업데이트 태스크 스케줄링
    private void scheduleTimeUpdateTask(GroupOrderDAO session) {
        String sessionId = session.getId();

        // 1초 후 첫 실행, 그 이후 1분마다 실행
        Instant initialDelay = Instant.now().plusSeconds(1);

        ScheduledFuture<?> timeUpdateTask = taskScheduler.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        try {

                            // 세션 조회
                            GroupOrderDAO currentSession = groupOrderRepositoryJPA.findById(sessionId).orElse(null);
                            if (currentSession != null) {
                                sendTimeUpdateMessage(currentSession);
                            } else {
                                ScheduledFuture<?> task = timeUpdateTasks.get(sessionId);
                                if (task != null) {
                                    task.cancel(false);
                                    timeUpdateTasks.remove(sessionId);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                initialDelay,
                Duration.ofSeconds(TIME_UPDATE_INTERVAL_SECONDS)
        );

        timeUpdateTasks.put(sessionId, timeUpdateTask);
    }

//     종료 전 알림 태스크 스케줄링
//    private void schedulePreSessionEndTask(GroupOrderDAO session, LocalDateTime warningTime) {
//        String sessionId = session.getId();
//        // 경고 메시지 태스크 예약
//        Instant warningInstant = warningTime.atZone(ZoneId.systemDefault()).toInstant();
//        ScheduledFuture<?> warningTask = taskScheduler.schedule(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            GroupOrderDAO currentSession = groupOrderRepositoryJPA.findById(sessionId).orElse(null);
//                            if (currentSession != null) {
//                                sendPreSessionEndMessage(currentSession);
//                            } else {
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                warningInstant
//        );
//        warningTasks.put(sessionId, warningTask);
//    }

//     종료 알림 태스크 스케줄링
//    private void scheduleSessionEndTask(GroupOrderDAO session, LocalDateTime expiryTime) {
//        String sessionId = session.getId();
//        Instant expiryInstant = expiryTime.atZone(ZoneId.systemDefault()).toInstant();
//        ScheduledFuture<?> endTask = taskScheduler.schedule(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            handleSessionExpiry(sessionId);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                expiryInstant
//        );
//        endTasks.put(sessionId, endTask);
//    }


    // 시간 업데이트 메시지 전송
    private void sendTimeUpdateMessage(GroupOrderDAO session) {
        String sessionId = session.getId();
        int remainingMinutes = getRemainingMinutes(sessionId);

        TimeInfo timeInfo = TimeInfo.builder()
                .remainingMinutes(remainingMinutes)
                .build();
        OrderMessageDTO message;
        if (remainingMinutes >= 2) {
            // 메시지 생성
            message = OrderMessageDTO.builder()
                    .type(TopicMessageType.TIMEUPDATE.name())
                    .boothId(session.getBoothId())
                    .tableNum(session.getTableNum())
                    .payload(timeInfo)
                    .build();
            String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
            messagingTemplate.convertAndSend(destination, message);

        } else if (remainingMinutes == 1) {
            message = OrderMessageDTO.builder()
                    .type(TopicMessageType.PRESESSIONEND.name())
                    .boothId(session.getBoothId())
                    .tableNum(session.getTableNum())
                    .payload(timeInfo)
                    .build();
            String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
            messagingTemplate.convertAndSend(destination, message);

        } else {
            message = OrderMessageDTO.builder()
                    .type(TopicMessageType.SESSIONEND.name())
                    .boothId(session.getBoothId())
                    .tableNum(session.getTableNum())
                    .payload(timeInfo)
                    .build();
            String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
            messagingTemplate.convertAndSend(destination, message);

            // 세션 삭제
            groupOrderRepositoryJPA.delete(session);
            cancelExistingTasks(sessionId);
        }
    }

    // 세션 만료 처리
//    @Transactional
//    public void handleSessionExpiry(String sessionId) {
//        GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId).orElse(null);
//
//        if (session != null) {
//            // 세션 종료 메시지 전송
//            sendSessionEndMessage(session);
//
//            // 세션 삭제
//            groupOrderRepositoryJPA.delete(session);
//        }
//
//        // 관련 태스크 정리
//        cancelExistingTasks(sessionId);
//    }

    // 기존 예약 태스크 취소
    public void cancelExistingTasks(String sessionId) {
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

//    // 세션 종료 전 경고 메시지 전송
//    private void sendPreSessionEndMessage(GroupOrderDAO session) {
//        int remainingMinutes = getRemainingMinutes(session.getId());
//
//        TimeInfo timeInfo = TimeInfo.builder()
//                .remainingMinutes(remainingMinutes)
//                .build();
//
//        OrderMessageDTO message = OrderMessageDTO.builder()
//                .type(TopicMessageType.PRESESSIONEND.name())
//                .boothId(session.getBoothId())
//                .tableNum(session.getTableNum())
//                .payload(timeInfo)
//                .build();
//
//        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
//        messagingTemplate.convertAndSend(destination, message);
//    }
//
//    // 세션 종료 메시지 전송
//    private void sendSessionEndMessage(GroupOrderDAO session) {
//        int remainingMinutes = getRemainingMinutes(session.getId());
//
//        TimeInfo timeInfo = TimeInfo.builder()
//                .remainingMinutes(remainingMinutes)
//                .build();
//
//        OrderMessageDTO message = OrderMessageDTO.builder()
//                .type(TopicMessageType.SESSIONEND.name())
//                .boothId(session.getBoothId())
//                .tableNum(session.getTableNum())
//                .payload(timeInfo)
//                .build();
//
//        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
//        messagingTemplate.convertAndSend(destination, message);
//    }

    // 세션 남은 시간 조회 (분 단위, 더 정확한 계산)
    public int getRemainingMinutes(String sessionId) {
        GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId).orElse(null);
        if (session != null && session.getExpiryTime() != null) {
            LocalDateTime now = DateTimeUtils.nowZone();;
            // 남은 시간 계산 - 초 단위로 계산 후 60으로 나누어 분으로 변환
            long remainingSeconds = Duration.between(now, session.getExpiryTime()).getSeconds();
            return (int) Math.max(0, (remainingSeconds + 59) / 60); // 올림 처리
        }
        return 0;
    }
}