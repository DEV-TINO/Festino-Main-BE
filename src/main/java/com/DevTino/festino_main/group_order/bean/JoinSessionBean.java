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
public class JoinSessionBean {

    private final GroupOrderRepositoryJPA groupOrderRepositoryJPA;
    private final SimpMessagingTemplate messagingTemplate;
    private final SessionTimeOutManage sessionTimeOutManage;

    @Autowired
    public JoinSessionBean(GroupOrderRepositoryJPA groupOrderRepositoryJPA, SimpMessagingTemplate messagingTemplate, SessionTimeOutManage sessionTimeOutManage) {
        this.groupOrderRepositoryJPA = groupOrderRepositoryJPA;
        this.messagingTemplate = messagingTemplate;
        this.sessionTimeOutManage = sessionTimeOutManage;
    }


    // 주문 참여
    @Transactional
    public void exec(UUID boothId, Integer tableNum, String webSocketSessionId, String clientId) {
        try {
            // 세션 조회 또는 생성
            GroupOrderDAO session = getOrCreateSession(boothId, tableNum, webSocketSessionId, clientId);

            // 멤버 업데이트 메시지 발송 (모든 클라이언트에게)
            sendMemberUpdateMessage(session);

            // 세션이 새로 생성된 경우에만 타임아웃 스케줄링
            // 참고: sessionTimeoutManager는 세션이 이미 있는 경우 타이머를 재설정하지 않음
            sessionTimeOutManage.scheduleSessionTimeout(boothId, tableNum);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 예외를 다시 던져서 글로벌 예외 처리기가 처리하도록 함
        }

    }

    // 세션 조회 또는 생성
    private GroupOrderDAO getOrCreateSession(UUID boothId, Integer tableNum, String webSocketSessionId, String clientId) {
        String sessionId = boothId + ":" + tableNum;
        GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId).orElse(null);

        if (session != null) {
            // 기존 세션이 있는 경우
            session.addClient(clientId);

        }else {
            // 세션이 없는 경우 새로 생성
            session = new GroupOrderDAO(boothId, tableNum, webSocketSessionId);
            session.addMemberCount();
        }

        // DB에 세션 저장
        groupOrderRepositoryJPA.save(session);
        // 트랜잭션이 아직 커밋되지 않았더라도 현재 변경사항을 즉시 DB에 반영
        groupOrderRepositoryJPA.flush();

        // 세션 반환
        return session;
    }

    // 멤버 업데이트 메시지 전송 (모든 클라이언트에게)
    private void sendMemberUpdateMessage(GroupOrderDAO session) {

        // 멤버 정보 생성
        MemberInfo memberInfo = MemberInfo.builder()
                .memberCount(session.getMemberCount())
                .build();

        // 메시지 생성
        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(String.valueOf(TopicMessageType.MEMBERUPDATE))
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .payload(memberInfo)
                .build();

        // 모든 구독자에게 메시지 전송
        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        messagingTemplate.convertAndSend(destination, message);
    }
}
