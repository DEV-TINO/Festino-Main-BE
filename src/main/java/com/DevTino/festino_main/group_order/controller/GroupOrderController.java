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

import java.util.Map;
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
            String sessionId = headerAccessor.getSessionId();
            String clientId = request.getClientId();

            if (AppMessageType.INIT.name().equals(typeStr)) {

                groupOrderService.joinOrderSession(request.getBoothId(), request.getTableNum(), sessionId, clientId);
                groupOrderService.sendInitMessage(request.getBoothId(), request.getTableNum(), sessionId);

            }
            else if (AppMessageType.TIMEUPDATE.name().equals(typeStr)) {
                // 시간 업데이트 요청 처리
                groupOrderService.sessionHealthCheck(request.getBoothId(), request.getTableNum(), sessionId, clientId);
            }

            else if (AppMessageType.MENUADD.name().equals(typeStr)) {
                // 메뉴 추가
                // payload가 Map으로 변환됨
                Map<String, Object> payload = (Map<String, Object>) request.getPayload();
                UUID menuId = UUID.fromString(payload.get("menuId").toString());
                groupOrderService.addMenu(request.getBoothId(), request.getTableNum(), menuId, clientId);
            }
            else if (AppMessageType.MENUSUB.name().equals(typeStr)) {
                // 메뉴 감소
                // payload가 Map으로 변환됨
                Map<String, Object> payload = (Map<String, Object>) request.getPayload();
                UUID menuId = UUID.fromString(payload.get("menuId").toString());
                groupOrderService.subMenu(request.getBoothId(), request.getTableNum(), menuId, clientId);
            }
            else if (AppMessageType.UNSUB.name().equals(typeStr)) {
                // 구독 취소
                groupOrderService.unSubOrderSession(request.getBoothId(), request.getTableNum(), sessionId, clientId);
            }
            else if (AppMessageType.STARTORDER.name().equals(typeStr)) {
                // 주문 시작
                groupOrderService.startOrder(request.getBoothId(), request.getTableNum(), clientId);
            }
            else if (AppMessageType.ORDERDONE.name().equals(typeStr)) {
                // 주문 완료
                groupOrderService.completeOrder(request.getBoothId(), request.getTableNum());
            }
            else if (AppMessageType.ORDERCANCEL.name().equals(typeStr)) {
                // 주문 취소
                groupOrderService.cancelOrder(request.getBoothId(), request.getTableNum());
            }

        } catch (Exception e) {
            // 에러 메시지 생성
            OrderMessageDTO errorMessage = OrderMessageDTO.builder()
                    .type(TopicMessageType.ERROR.name()) // 문자열로 설정
                    .boothId(request.getBoothId())
                    .tableNum(request.getTableNum())
                    .payload("요청 처리 중 오류 발생: " + e.getMessage())
                    .build();

            // 에러 메시지 전송
            messagingTemplate.convertAndSend("/topic/" + request.getBoothId() + "/" + request.getTableNum(), errorMessage);
        }
    }

//    @EventListener
//    public void handleSubscription(SessionSubscribeEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String destination = headerAccessor.getDestination();
//        String sessionId = headerAccessor.getSessionId();
//
//        try {
//            if (destination != null) {
//                String[] parts = destination.split("/");
//                UUID boothId = null;
//                Integer tableNum = null;
//
//                // 일반 토픽 구독: /topic/{boothId}/{tableNum}
//                if (destination.startsWith("/topic/") && parts.length == 4) {
//                    boothId = UUID.fromString(parts[2]);
//                    tableNum = Integer.parseInt(parts[3]);
//                    groupOrderService.joinOrderSession(boothId, tableNum);
//                }
//                // 사용자별 토픽 구독: /user/topic/{boothId}/{tableNum}
//                else if (destination.startsWith("/user/") && parts.length >= 5 && "topic".equals(parts[2])) {
//                    boothId = UUID.fromString(parts[3]);
//                    tableNum = Integer.parseInt(parts[4]);
//                    groupOrderService.sendInitMessage(boothId, tableNum, sessionId);
//                }
//            }
//        } catch (Exception e) {
//            log.error("구독 처리 오류: {}", e.getMessage(), e);
//        }
//    }

}
