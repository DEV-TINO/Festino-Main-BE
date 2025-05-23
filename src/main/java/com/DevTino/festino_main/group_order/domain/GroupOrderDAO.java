package com.DevTino.festino_main.group_order.domain;

import com.DevTino.festino_main.DateTimeUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupOrderDAO {

    @Id
    private String id;

    UUID boothId;
    Integer tableNum;


    Integer memberCount = 0;
    Integer totalPrice = 0;
    Integer totalCount = 0;

    boolean orderInProgress = false;
    String orderInitiatorId = null;

    // 세션 시작 시간
    LocalDateTime startTime;

    // 세션 만료 시간
    LocalDateTime expiryTime;

    // client ID
    @ElementCollection
    private List<String> clientIds = new ArrayList<>();

    // 주문 메뉴 일대다 관계
    @OneToMany(mappedBy = "groupOrderDAO", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupOrderMenuDAO> menuItems = new ArrayList<>();

    // 주문 세션 생성자
    public GroupOrderDAO(UUID boothId, Integer tableNum, String clientId) {
        this.boothId = boothId;
        this.tableNum = tableNum;
        this.id = boothId + ":" + tableNum;  // 복합 ID 생성
        this.startTime = DateTimeUtils.nowZone();
        this.expiryTime = this.startTime.plusMinutes(10); // 10분 후 만료
    }

    // 참여 인원 증가
    public void addMemberCount() {
        this.memberCount++;
    }

    // 클라이언트 추가
    public void addClient(String clientId) {
        if (this.clientIds.contains(clientId)) {
            this.clientIds.add(clientId);
            this.memberCount++;
        }
    }

    // 클라이언트 제거
    public void removeClient(String clientId) {
        this.clientIds.remove(clientId);
        this.memberCount--;
    }

    // 메뉴 추가
    public void addMenu(UUID menuId, int price) {
        // 기존 메뉴 항목 찾기
        GroupOrderMenuDAO menuItem = findMenuItem(menuId);

        if (menuItem != null) {
            // 기존 메뉴 수량 증가
            menuItem.increaseCount();
        } else {
            // 새 메뉴 항목 추가
            menuItem = new GroupOrderMenuDAO(this, menuId, 1);
            menuItems.add(menuItem);
        }

        // 전체 카운트 및 가격 업데이트
        this.totalCount++;
        this.totalPrice += price;
    }

    // 메뉴 감소
    public void subMenu(UUID menuId, int price) {
        GroupOrderMenuDAO menuItem = findMenuItem(menuId);

        if (menuItem != null && menuItem.getMenuCount() > 0) {
            menuItem.decreaseCount();

            // 수량이 0이면 항목 제거
            if (menuItem.getMenuCount() == 0) {
                menuItems.remove(menuItem);
            }

            this.totalCount--;
            this.totalPrice -= price;
        }
    }

    // 메뉴 수량 조회
    public Integer getMenuCount(UUID menuId) {
        GroupOrderMenuDAO menuItem = findMenuItem(menuId);
        return menuItem != null ? menuItem.getMenuCount() : 0;
    }

    // 특정 메뉴 항목 찾기
    private GroupOrderMenuDAO findMenuItem(UUID menuId) {
        return menuItems.stream()
                .filter(item -> item.getMenuId().equals(menuId))
                .findFirst()
                .orElse(null);
    }

    // 주문 시작 상태 설정
    public void startOrder(String initiatorSessionId) {
        this.orderInProgress = true;
        this.orderInitiatorId = initiatorSessionId;
    }

    // 주문 상태 초기화
    public void resetOrderStatus() {
        this.orderInProgress = false;
        this.orderInitiatorId = null;
    }
}
