package com.DevTino.festino_main.group_order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSession implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    UUID boothId;
    Integer tableNum;
    Integer memberCount = 0;
    Integer totalPrice = 0;
    Integer totalCount = 0;
    Map<UUID, Integer> menuCounts = new ConcurrentHashMap<>();
    @JsonIgnore  // Redis에 저장X (세션이 종료되기 1분 전에 알림을 보내는 작업을 하기 위한 필드)
    private transient ScheduledFuture<?> sessionEndTask;
    @JsonIgnore  // Redis에 저장X (세션을 완전히 종료하는 작업을 하기 위한 필드)
    private transient ScheduledFuture<?> preSessionEndTask;    // 세션 종료 전 알림 타이머 작업
    private long sessionCreatedAt; // 세션 생성 시간
    private long sessionExpiryTime; // 세션 만료 시간

    // 주문 세션 생성자
    public OrderSession(UUID boothId, Integer tableNum) {
        this.boothId = boothId;
        this.tableNum = tableNum;
        this.sessionCreatedAt = System.currentTimeMillis();
        // 기본 만료 시간 설정 (10분 후)
        this.sessionExpiryTime = this.sessionCreatedAt + (10 * 60 * 1000);
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


    // 예약된 타이머 작업 취소
    /*
    * 세션이 조기에 종료될 때 예약된 타이머 작업을 취소

    * 이 함수가 호출되는 경우
    * 1. 사용자가 주문을 완료할 때
    * 2. 모든 사용자가 세션을 떠날 때
    * 3. 세션이 다른 이유로 조기에 종료될 때
    * */
    public boolean cancelScheduledTasks() {
        boolean result = true;
        if (sessionEndTask != null) {
            result = sessionEndTask.cancel(false);
        }
        if (preSessionEndTask != null) {
            result = result && preSessionEndTask.cancel(false);
        }
        return result;
    }
}
