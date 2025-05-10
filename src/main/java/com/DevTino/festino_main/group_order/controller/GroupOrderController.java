package com.DevTino.festino_main.group_order.controller;

import com.DevTino.festino_main.group_order.domain.DTO.OrderMessageDTO;
import com.DevTino.festino_main.group_order.domain.ENUM.AppMessageType;
import com.DevTino.festino_main.group_order.domain.ENUM.TopicMessageType;
import com.DevTino.festino_main.group_order.service.GroupOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GroupOrderController {

    private final GroupOrderService groupOrderService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/order")
    public void processOrder(OrderMessageDTO request, SimpMessageHeaderAccessor headerAccessor) {
        try {
            String typeStr = request.getType();

            if (AppMessageType.MENUADD.name().equals(typeStr)) {
                // 메뉴 추가
                groupOrderService.addMenu(request.getBoothId(), request.getTableNum(), request.getMenuInfo().getMenuId());
            }
            else if (AppMessageType.MENUSUB.name().equals(typeStr)) {
                // 메뉴 감소
                groupOrderService.subMenu(request.getBoothId(), request.getTableNum(), request.getMenuInfo().getMenuId());
            }
            else if (AppMessageType.UNSUB.name().equals(typeStr)) {
                // 구독 취소
                String sessionId = headerAccessor.getSessionId();
                groupOrderService.unSubOrderSession(request.getBoothId(), request.getTableNum(), sessionId);
            }
            // 기타 타입 처리...
        } catch (Exception e) {
            // 에러 메시지 생성
            OrderMessageDTO errorMessage = OrderMessageDTO.builder()
                    .type(TopicMessageType.ERROR.name()) // 문자열로 설정
                    .boothId(request.getBoothId())
                    .tableNum(request.getTableNum())
                    .errorMessage("요청 처리 중 오류 발생: " + e.getMessage())
                    .build();

            // 에러 메시지 전송
            messagingTemplate.convertAndSend("/topic/" + request.getBoothId() + "/" + request.getTableNum(), errorMessage);
        }
    }

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
