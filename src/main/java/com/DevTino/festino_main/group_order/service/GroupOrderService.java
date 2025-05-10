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

    // 주문 세션 참여 - subscirbe 시 호출됨
    public void joinOrderSession(UUID boothId, Integer tableNum, String sessionId) {
        joinSessionBean.exec(boothId, tableNum, sessionId);
    }

    // 메뉴 추가
    public void addMenu(UUID boothId, Integer tableNum, UUID menuId) {
        menuAddBean.exec(boothId, tableNum, menuId);
    }

    // 메뉴 감소
    public void subMenu(UUID boothId, Integer tableNum, UUID menuId) {
        menuSubBean.exec(boothId, tableNum, menuId);
    }

    // 구독 취소
    public void unSubOrderSession(UUID boothId, Integer tableNum, String sessionId) {
        unsubSessionBean.exec(boothId, tableNum, sessionId);
    }

    // 주문 시작
    public void startOrder(UUID boothId, Integer tableNum, String sessionId) {
        startOrderBean.exec(boothId, tableNum, sessionId);
    }
}
