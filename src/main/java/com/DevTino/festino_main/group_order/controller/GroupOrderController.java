package com.DevTino.festino_main.group_order.controller;

import com.DevTino.festino_main.group_order.service.GroupOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GroupOrderController {

    private final GroupOrderService groupOrderService;

    @EventListener
    public void handleSubscription(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String destination = headerAccessor.getDestination();

        // topic/{boothId}/{tableNum} 형식 처리
        if (destination != null && destination.startsWith("/topic/")) {
            String[] parts = destination.split("/");
            if (parts.length == 4) {
                try {
                    UUID boothId = UUID.fromString(parts[2]);
                    Integer tableNum = Integer.parseInt(parts[3]);
                    String sessionId = headerAccessor.getSessionId();

                    // 주문 세션 참여 처리
                    groupOrderService.joinOrderSession(boothId, tableNum, sessionId);
                } catch (Exception e) {
                    log.error("구독 처리 오류", e);
                }
            }
        }
    }

}
