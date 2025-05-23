package com.DevTino.festino_main.group_order.bean;

import com.DevTino.festino_main.group_order.domain.DTO.OrderMessageDTO;
import com.DevTino.festino_main.group_order.domain.ENUM.TopicMessageType;
import com.DevTino.festino_main.group_order.domain.GroupOrderDAO;
import com.DevTino.festino_main.group_order.repository.GroupOrderRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class StartOrderBean {

    private final GroupOrderRepositoryJPA groupOrderRepositoryJPA;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public StartOrderBean(GroupOrderRepositoryJPA groupOrderRepositoryJPA, SimpMessagingTemplate messagingTemplate) {
        this.groupOrderRepositoryJPA = groupOrderRepositoryJPA;
        this.messagingTemplate = messagingTemplate;
    }

    @Transactional
    public void exec(UUID boothId, Integer tableNum, String clientId) {
        try {
            // 세션 ID 생성
            String sessionId = boothId + ":" + tableNum;

            // 세션 조회
            GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Order session not found: " + sessionId));

            // 주문 진행 중 상태 설정 및 주문시작 버튼 누른 사용자 설정
            session.startOrder(clientId);
            session.updateClientActivity(clientId);

            // 세션 저장
            groupOrderRepositoryJPA.save(session);
            groupOrderRepositoryJPA.flush();

            // 주문 시작 메시지 전송 (해당 클라이언트 외 모두에게)
            sendStartOrderMessage(session, clientId);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 주문 시작 메시지 전송 (해당 클라이언트 외 모두에게)
    private void sendStartOrderMessage(GroupOrderDAO session, String excludeSessionId) {
        // 메시지 생성
        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(TopicMessageType.STARTORDER.name())
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .clientId(session.getOrderInitiatorId())
                .build();

        // 메시지 전송 (특정 세션 ID 제외)
        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();

        // SimpMessagingTemplate에는 특정 세션을 제외하는 메서드 X
        // 헤더에 제외할 세션 ID를 포함시켜 브로드캐스트
        messagingTemplate.convertAndSend(destination, message,
                excludeUser("excludeSessionId", excludeSessionId));
    }

    private MessageHeaders excludeUser(String key, String value) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(key, value);
        return new MessageHeaders(headers);
    }
}
