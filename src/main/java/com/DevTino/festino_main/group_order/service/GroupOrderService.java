package com.DevTino.festino_main.group_order.service;

import com.DevTino.festino_main.group_order.bean.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupOrderService {

    private final JoinSessionBean joinSessionBean;
    private final MenuAddBean menuAddBean;
    private final MenuSubBean menuSubBean;
    private final UnSubSessionBean unsubSessionBean;
    private final StartOrderBean startOrderBean;
    private final SendInitMessageBean sendInitMessageBean;
    private final OrderDoneBean orderDoneBean;
    private final OrderCancelBean orderCancelBean;
    private final SessionHealthCheckBean sessionHealthCheckBean;

    // 주문 세션 참여 - 전체 클라이언트에게 전송용
    public void joinOrderSession(UUID boothId, Integer tableNum, String sessionId, String clientId) {
        joinSessionBean.exec(boothId, tableNum, sessionId, clientId);
    }

    // 주문 세션 참여 - 특정 클라이언트에게 init메시지 전송용
    public void sendInitMessage(UUID boothId, Integer tableNum, String sessionId) {
        sendInitMessageBean.exec(boothId, tableNum, sessionId);
    }
    // 메뉴 추가
    public void sessionHealthCheck(UUID boothId, Integer tableNum, String sessionId, String clientId) {
        sessionHealthCheckBean.exec(boothId, tableNum, sessionId, clientId);
    }

    // 메뉴 추가
    public void addMenu(UUID boothId, Integer tableNum, UUID menuId,  String clientId) {
        menuAddBean.exec(boothId, tableNum, menuId, clientId);
    }

    // 메뉴 감소
    public void subMenu(UUID boothId, Integer tableNum, UUID menuId,  String clientId) {
        menuSubBean.exec(boothId, tableNum, menuId, clientId);
    }

    // 구독 취소
    public void unSubOrderSession(UUID boothId, Integer tableNum, String sessionId, String clientId) {
        unsubSessionBean.exec(boothId, tableNum, sessionId, clientId);
    }

    // 주문 시작
    public void startOrder(UUID boothId, Integer tableNum, String clientId) {
        startOrderBean.exec(boothId, tableNum, clientId);
    }

    // 주문 완료
    public void completeOrder(UUID boothId, Integer tableNum) {
        orderDoneBean.exec(boothId, tableNum);
    }

    // 주문 취소
    public void cancelOrder(UUID boothId, Integer tableNum) {
        orderCancelBean.exec(boothId, tableNum);
    }
}
