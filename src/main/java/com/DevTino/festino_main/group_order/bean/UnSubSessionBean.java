package com.DevTino.festino_main.group_order.bean;

import com.DevTino.festino_main.group_order.domain.DTO.MemberInfo;
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
public class UnSubSessionBean {
    private final GroupOrderRepositoryJPA groupOrderRepositoryJPA;
    private final SimpMessagingTemplate messagingTemplate;
    private final SessionTimeOutManage sessionTimeOutManage;

    @Autowired
    public UnSubSessionBean(GroupOrderRepositoryJPA groupOrderRepositoryJPA, SimpMessagingTemplate messagingTemplate, SessionTimeOutManage sessionTimeOutManage) {
        this.groupOrderRepositoryJPA = groupOrderRepositoryJPA;
        this.messagingTemplate = messagingTemplate;
        this.sessionTimeOutManage = sessionTimeOutManage;
    }

    @Transactional
    public void exec(UUID boothId, Integer tableNum, String webSocketSessionId, String clientId) {
        try {
            // 세션 ID 생성
            String sessionId = boothId + ":" + tableNum;

            // 세션 조회
            GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Order session not found: " + sessionId));

            // 해당 클라이언트에게 UNSUB 메시지 전송
            sendUnSubMessage(webSocketSessionId, boothId, tableNum);

            // 멤버 감소
            session.removeClient(clientId);

            // 세션 저장 또는 삭제
            if (session.getMemberCount() <= 0) {
                // 0명이면 세션 삭제
                groupOrderRepositoryJPA.delete(session);
                // 모든 타이머 태스크 취소
                sessionTimeOutManage.cancelExistingTasks(sessionId);
            } else {
                // 멤버가 있으면 세션 업데이트
                groupOrderRepositoryJPA.save(session);

                // 나머지 클라이언트에게 멤버 업데이트 메시지 전송
                sendMemberUpdateMessage(session);
            }
            
            groupOrderRepositoryJPA.flush();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 해당 클라이언트에게 UNSUB 메시지 전송
    private void sendUnSubMessage(String webSocketSessionId, UUID boothId, Integer tableNum) {
        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(TopicMessageType.UNSUB.name())
                .boothId(boothId)
                .tableNum(tableNum)
                .build();

        String destination = "/topic/" + boothId + "/" + tableNum;
        messagingTemplate.convertAndSendToUser(webSocketSessionId, destination, message);
    }

    // 모든 클라이언트에게 멤버 업데이트 메시지 전송
    private void sendMemberUpdateMessage(GroupOrderDAO session) {
        MemberInfo memberInfo = MemberInfo.builder()
                .memberCount(session.getMemberCount())
                .build();

        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(TopicMessageType.MEMBERUPDATE.name())
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .payload(memberInfo)
                .build();

        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        messagingTemplate.convertAndSend(destination, message);
    }
}
