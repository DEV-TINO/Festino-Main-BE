package com.DevTino.festino_main.group_order.bean;

import com.DevTino.festino_main.group_order.domain.DTO.InitInfo;
import com.DevTino.festino_main.group_order.domain.DTO.MemberInfo;
import com.DevTino.festino_main.group_order.domain.DTO.MenuInfo;
import com.DevTino.festino_main.group_order.domain.DTO.OrderMessageDTO;
import com.DevTino.festino_main.group_order.domain.ENUM.TopicMessageType;
import com.DevTino.festino_main.group_order.domain.GroupOrderDAO;
import com.DevTino.festino_main.group_order.repository.OrderSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JoinSessionBean {

    private final OrderSessionRepository orderSessionRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public JoinSessionBean(OrderSessionRepository orderSessionRepository, SimpMessagingTemplate messagingTemplate) {
        this.orderSessionRepository = orderSessionRepository;
        this.messagingTemplate = messagingTemplate;
    }

    // 주문 참여
    public void exec(UUID boothId, Integer tableNum, String webSocketSessionId) {
        // 세션 조회 또는 생성
        GroupOrderDAO session = getOrCreateSession(boothId, tableNum);

        // 초기화 메시지 발송 (해당 클라이언트에게만)
        sendInitMessage(webSocketSessionId, session);

        // 멤버 업데이트 메시지 발송 (모든 클라이언트에게)
        sendMemberUpdateMessage(session);

    }

    // 세션 조회 또는 생성
    private GroupOrderDAO getOrCreateSession(UUID boothId, Integer tableNum) {
        String sessionId = boothId + ":" + tableNum;
        GroupOrderDAO session = orderSessionRepository.findById(sessionId).orElse(null);

        if (session != null) {
            // 기존 세션이 있는 경우
            session.addMemberCount();
        } else {
            // 세션이 없는 경우 새로 생성
            session = new GroupOrderDAO(boothId, tableNum);
            session.addMemberCount();
        }

        // DB에 세션 저장
        orderSessionRepository.save(session);

        // 세션 반환
        return session;
    }

    // 초기화 메시지 전송 (해당 클라이언트에게만)
    private void sendInitMessage(String webSocketSessionId, GroupOrderDAO session) {

        // 메뉴 목록 Map -> List 형태로
        List<MenuInfo> menuInfoList = session.getMenuCounts().entrySet().stream()
                .map(entry -> MenuInfo.builder()
                        .menuId(entry.getKey())
                        .menuCount(entry.getValue())
                        .build())
                .toList();

        // 초기화 정보 생성
        InitInfo initInfo = InitInfo.builder()
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .memberCount(session.getMemberCount())
                .totalPrice(session.getTotalPrice())
                .totalCount(session.getTotalCount())
                .menuList(menuInfoList)
                .build();

        // 메시지 생성
        OrderMessageDTO initMessage = OrderMessageDTO.builder()
                .type(TopicMessageType.INIT)
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .initInfo(initInfo)
                .build();

        // 특정 사용자에게 메시지 전송 (해당 클라이언트에게만)
        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        messagingTemplate.convertAndSendToUser(webSocketSessionId, destination, initMessage);
    }

    // 멤버 업데이트 메시지 전송 (모든 클라이언트에게)
    private void sendMemberUpdateMessage(GroupOrderDAO session) {

        // 멤버 정보 생성
        MemberInfo memberInfo = MemberInfo.builder()
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .memberCount(session.getMemberCount())
                .build();

        // 메시지 생성
        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(TopicMessageType.MEMBERUPDATE)
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .memberInfo(memberInfo)
                .build();

        // 모든 구독자에게 메시지 전송
        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        messagingTemplate.convertAndSend(destination, message);
    }
}
