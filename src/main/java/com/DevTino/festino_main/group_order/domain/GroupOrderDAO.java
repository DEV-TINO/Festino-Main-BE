package com.DevTino.festino_main.group_order.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ElementCollection
    @CollectionTable(name = "group_order_menu_counts",
            joinColumns = @JoinColumn(name = "group_order_id"))
    @MapKeyColumn(name = "menu_id")
    @Column(name = "count")
    private Map<UUID, Integer> menuCounts = new HashMap<>();

    // 주문 세션 생성자
    public GroupOrderDAO(UUID boothId, Integer tableNum) {
        this.boothId = boothId;
        this.tableNum = tableNum;
        this.id = boothId + ":" + tableNum;  // 복합 ID 생성
    }

    // 참여 인원 증가
    public void addMemberCount() {
        this.memberCount++;
    }

    // 참여 인원 감소
    public void subMemberCount() {
        this.memberCount--;
    }

    // 메뉴 추가
    public void addMenu(UUID menuId, int price) {
        int currentCount = menuCounts.getOrDefault(menuId, 0);
        menuCounts.put(menuId, currentCount + 1);
        this.totalCount++;
        this.totalPrice += price;
    }

    // 메뉴 감소
    public void subMenu(UUID menuId, int price) {
        int currentCount = menuCounts.getOrDefault(menuId, 0);
        if (currentCount > 0) {
            menuCounts.put(menuId, currentCount - 1);
            this.totalCount--;
            this.totalPrice -= price;
        }
    }

    // 메뉴 수량 조회
    public Integer getMenuCount(UUID menuId) {
        return menuCounts.getOrDefault(menuId, 0);
    }
}
