package com.DevTino.festino_main.group_order.bean;

import com.DevTino.festino_main.group_order.domain.DTO.MenuInfo;
import com.DevTino.festino_main.group_order.domain.DTO.OrderMessageDTO;
import com.DevTino.festino_main.group_order.domain.ENUM.TopicMessageType;
import com.DevTino.festino_main.group_order.domain.GroupOrderDAO;
import com.DevTino.festino_main.group_order.repository.GroupOrderRepositoryJPA;
import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import com.DevTino.festino_main.menu.repository.MenuRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class MenuAddBean {
    private final GroupOrderRepositoryJPA groupOrderRepositoryJPA;
    private final MenuRepositoryJPA menuRepositoryJPA;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MenuAddBean(GroupOrderRepositoryJPA groupOrderRepositoryJPA, MenuRepositoryJPA menuRepositoryJPA, SimpMessagingTemplate messagingTemplate) {
        this.groupOrderRepositoryJPA = groupOrderRepositoryJPA;
        this.menuRepositoryJPA = menuRepositoryJPA;
        this.messagingTemplate = messagingTemplate;
    }

    @Transactional
    public void exec(UUID boothId, Integer tableNum, UUID menuId) {
        System.out.println("MenuAddBean.exec 시작: boothId=" + boothId + ", tableNum=" + tableNum + ", menuId=" + menuId);

        // 세션 id 생성
        String sessionId = boothId + ":" + tableNum;
        System.out.println("세션 ID: " + sessionId);

        try {
            // 세션 조회
            GroupOrderDAO session = groupOrderRepositoryJPA.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Order session not found: " + sessionId));
            System.out.println("세션 조회 성공: " + session);

            // 메뉴 조회
            MenuDAO menu = menuRepositoryJPA.findById(menuId)
                    .orElseThrow(() -> new RuntimeException("Menu not found: " + menuId));
            System.out.println("메뉴 조회 성공: " + menu.getMenuName());

            // 메뉴 가격 가져오기
            int price = menu.getMenuPrice();

            // 메뉴 수량 증가
            session.addMenu(menuId, price);
            System.out.println("메뉴 수량 증가 후: count=" + session.getMenuCount(menuId));

            // 세션 저장
            groupOrderRepositoryJPA.save(session);
            System.out.println("세션 저장 완료");

            // 메뉴 업데이트 메시지 전송
            sendMenuUpdateMessage(session, menuId);
            System.out.println("메뉴 업데이트 메시지 전송 완료");
        } catch (Exception e) {
            System.err.println("메뉴 추가 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            throw e; // 예외를 다시 던져서 글로벌 예외 처리기가 처리하도록 함
        }
    }

    private void sendMenuUpdateMessage(GroupOrderDAO session, UUID menuId) {
        System.out.println("메뉴 업데이트 메시지 전송 시작: menuId=" + menuId);

        // 메뉴 정보 생성
        MenuInfo menuInfo = MenuInfo.builder()
                .menuId(menuId)
                .menuCount(session.getMenuCount(menuId))
                .build();
        System.out.println("메뉴 정보: " + menuInfo);

        // 메시지 생성
        OrderMessageDTO message = OrderMessageDTO.builder()
                .type(String.valueOf(TopicMessageType.MENUUPDATE))
                .boothId(session.getBoothId())
                .tableNum(session.getTableNum())
                .menuInfo(menuInfo)
                .build();
        System.out.println("메시지 생성: " + message);

        // 모든 구독자에게 메시지 전송
        String destination = "/topic/" + session.getBoothId() + "/" + session.getTableNum();
        System.out.println("메시지 전송 대상: " + destination);

        messagingTemplate.convertAndSend(destination, message);
        System.out.println("메시지 전송 완료");
    }
}
