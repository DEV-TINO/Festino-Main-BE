package com.DevTino.festino_main.group_order.bean;

import com.DevTino.festino_main.group_order.domain.DTO.InitInfo;
import com.DevTino.festino_main.group_order.domain.DTO.MenuInfo;
import com.DevTino.festino_main.group_order.domain.DTO.OrderMessageDTO;
import com.DevTino.festino_main.group_order.domain.ENUM.TopicMessageType;
import com.DevTino.festino_main.group_order.domain.GroupOrderDAO;
import com.DevTino.festino_main.group_order.repository.GroupOrderRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class SendInitMessageBean {
    private final GroupOrderRepositoryJPA groupOrderRepositoryJPA;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SendInitMessageBean(GroupOrderRepositoryJPA groupOrderRepositoryJPA, SimpMessagingTemplate messagingTemplate) {
        this.groupOrderRepositoryJPA = groupOrderRepositoryJPA;
        this.messagingTemplate = messagingTemplate;
    }

    // 초기화 메시지만 발송 (참여자 수 증가 없음)
    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public void exec(UUID boothId, Integer tableNum, String webSocketSessionId) {
        try {
            // 세션 조회 (참여자 수 증가 없음)
            String sessionId = boothId + ":" + tableNum;
            GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Order session not found: " + sessionId));

            sendInitMessage(webSocketSessionId, session);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 초기화 메시지 전송 (해당 클라이언트에게만)
    private void sendInitMessage(String webSocketSessionId, GroupOrderDAO session) {

        // 메뉴 목록 List로 변환
        List<MenuInfo> menuInfoList = session.getMenuItems().stream()
                .map(item -> MenuInfo.builder()
                        .menuId(item.getMenuId())
                        .menuCount(item.getMenuCount())
                        .build())
                .toList();

        // 초기화 정보 생성
        InitInfo initInfo = InitInfo.builder()
                .memberCount(session.getMemberCount())
                .totalPrice(session.getTotalPrice())
                .totalCount(session.getTotalCount())
                .menuList(menuInfoList)
                .build();

        // 메시지 생성
        OrderMessageDTO initMessage = OrderMessageDTO.builder()
                .type(String.valueOf(TopicMessageType.INIT))
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .payload(initInfo)
                .build();

        // 특정 사용자에게 메시지 전송 (해당 클라이언트에게만)
        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        messagingTemplate.convertAndSendToUser(webSocketSessionId, destination, initMessage);
    }
}
