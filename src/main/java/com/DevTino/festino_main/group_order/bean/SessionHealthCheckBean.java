package com.DevTino.festino_main.group_order.bean;


import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.group_order.domain.DTO.OrderMessageDTO;
import com.DevTino.festino_main.group_order.domain.ENUM.TopicMessageType;
import com.DevTino.festino_main.group_order.domain.GroupOrderDAO;
import com.DevTino.festino_main.group_order.repository.GroupOrderRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SessionHealthCheckBean {
    private final GroupOrderRepositoryJPA groupOrderRepositoryJPA;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SessionHealthCheckBean(GroupOrderRepositoryJPA groupOrderRepositoryJPA, SimpMessagingTemplate messagingTemplate) {
        this.groupOrderRepositoryJPA = groupOrderRepositoryJPA;
        this.messagingTemplate = messagingTemplate;
    }

    public void exec(UUID boothId, Integer tableNum) {
        try {
            // 세션 ID 생성
            String sessionId = boothId + ":" + tableNum;

            // 세션 조회
            GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Order session not found: " + sessionId));

            // 헬스 체크 메시지 전송 (해당 클라이언트 외 모두에게)
            sendHealthCheckMessage(session);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    // 헬스 체크 메시지 전송
    private void sendHealthCheckMessage(GroupOrderDAO session) {

        // 남은 시간 계산 (sessionTimeOutManage를 주입받아야 함)
        int remainingMinutes = getRemainingMinutes(session.getId());

        // 메시지 생성
        OrderMessageDTO message;

        if (remainingMinutes >= 2) {
            // 메시지 생성
            message = OrderMessageDTO.builder()
                    .type(TopicMessageType.TIMEUPDATE.name())
                    .boothId(session.getBoothId())
                    .tableNum(session.getTableNum())
                    .payload(remainingMinutes)
                    .build();
            String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
            messagingTemplate.convertAndSend(destination, message);

        } else if (remainingMinutes == 1) {
            message = OrderMessageDTO.builder()
                    .type(TopicMessageType.PRESESSIONEND.name())
                    .boothId(session.getBoothId())
                    .tableNum(session.getTableNum())
                    .payload(remainingMinutes)
                    .build();
            String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
            messagingTemplate.convertAndSend(destination, message);

        } else if (remainingMinutes == 0){
            message = OrderMessageDTO.builder()
                    .type(TopicMessageType.SESSIONEND.name())
                    .boothId(session.getBoothId())
                    .tableNum(session.getTableNum())
                    .payload(remainingMinutes)
                    .build();
            String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
            messagingTemplate.convertAndSend(destination, message);

            // 세션 삭제
            groupOrderRepositoryJPA.delete(session);
        } else {
            System.out.println("CannotConvertReamin");
        }
    }

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
