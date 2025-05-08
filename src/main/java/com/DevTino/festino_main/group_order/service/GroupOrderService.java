package com.DevTino.festino_main.group_order.service;

import com.DevTino.festino_main.group_order.bean.JoinSessionBean;
import com.DevTino.festino_main.group_order.bean.MenuAddBean;
import com.DevTino.festino_main.group_order.bean.MenuSubBean;
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

    // 주문 세션 참여 - connect 시 호출됨
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
}
