package com.DevTino.festino_main.group_order.bean;

import com.DevTino.festino_main.group_order.domain.DTO.OrderMessageDTO;
import com.DevTino.festino_main.group_order.domain.DTO.TimeInfo;
import com.DevTino.festino_main.group_order.domain.ENUM.TopicMessageType;
import com.DevTino.festino_main.group_order.domain.GroupOrderDAO;
import com.DevTino.festino_main.group_order.repository.GroupOrderRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SessionClean {

    private final GroupOrderRepositoryJPA groupOrderRepositoryJPA;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SessionClean(GroupOrderRepositoryJPA groupOrderRepositoryJPA, SimpMessagingTemplate messagingTemplate) {
        this.groupOrderRepositoryJPA = groupOrderRepositoryJPA;
        this.messagingTemplate = messagingTemplate;
    }

    // 5분마다 실행
    @Scheduled(fixedRate = 300000)
    @Transactional
    public void cleanupExpiredSessions() {
        // 현재시간
        LocalDateTime now = LocalDateTime.now();

        // 전체 세션 조회
        List<GroupOrderDAO> allSessions = groupOrderRepositoryJPA.findAll();

        // 각 세션마다
        for (GroupOrderDAO session : allSessions) {
            // 만료 시간이 이미 지났으면
            if (session.getExpiryTime() != null && session.getExpiryTime().isBefore(now)) {

                // 세션 종료 메시지 전송
                sendSessionEndMessage(session);

                // 세션 삭제
                groupOrderRepositoryJPA.delete(session);
            }
        }
        // 즉시 DB에 반영되도록
        groupOrderRepositoryJPA.flush();
    }

    // 세션 종료 메시지 전송
    private void sendSessionEndMessage(GroupOrderDAO session) {
        try {

            TimeInfo timeInfo = TimeInfo.builder()
                    .remainingMinutes(0)
                    .build();

            OrderMessageDTO message = OrderMessageDTO.builder()
                    .type(TopicMessageType.SESSIONEND.name())
                    .boothId(session.getBoothId())
                    .tableNum(session.getTableNum())
                    .payload(timeInfo)
                    .build();

            String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
            messagingTemplate.convertAndSend(destination, message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
