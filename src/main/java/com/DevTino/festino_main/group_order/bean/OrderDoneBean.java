package com.DevTino.festino_main.group_order.bean;

import com.DevTino.festino_main.group_order.domain.DTO.MenuInfo;
import com.DevTino.festino_main.group_order.domain.DTO.OrderMessageDTO;
import com.DevTino.festino_main.group_order.domain.ENUM.TopicMessageType;
import com.DevTino.festino_main.group_order.domain.GroupOrderDAO;
import com.DevTino.festino_main.group_order.repository.GroupOrderRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class OrderDoneBean {
    GroupOrderRepositoryJPA groupOrderRepositoryJPA;
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    public OrderDoneBean(GroupOrderRepositoryJPA groupOrderRepositoryJPA, SimpMessagingTemplate messagingTemplate) {
        this.groupOrderRepositoryJPA = groupOrderRepositoryJPA;
        this.messagingTemplate = messagingTemplate;
    }

    @Transactional
    public void exec(UUID boothId, Integer tableNum) {
        try {
            // 세션 ID 생성
            String sessionId = boothId + ":" + tableNum;

            // 세션 조회
            GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Order session not found: " + sessionId));

            // 주문 완료 메시지 전송 (모든 클라이언트에게)
            sendOrderDoneMessage(session);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 주문 완료 메시지 전송
    private void sendOrderDoneMessage(GroupOrderDAO session) {

        // 메시지 생성
        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(TopicMessageType.ORDERDONE.name())
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .build();

        // 모든 구독자에게 메시지 전송
        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        messagingTemplate.convertAndSend(destination, message);
    }
}
