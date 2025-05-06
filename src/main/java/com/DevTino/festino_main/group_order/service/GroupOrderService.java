package com.DevTino.festino_main.group_order.service;

import com.DevTino.festino_main.group_order.bean.JoinSessionBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupOrderService {

    private final JoinSessionBean joinSessionBean;

    // 주문 세션 참여 - connect 시 호출됨
    public void joinOrderSession(UUID boothId, Integer tableNum, String sessionId) {
        joinSessionBean.exec(boothId, tableNum, sessionId);
    }
}
