package com.DevTino.festino_main.group_order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupOrderMenuDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GroupOrderDAO groupOrderDAO;

    private UUID menuId;
    private Integer menuCount;

    // 생성자
    public GroupOrderMenuDAO(GroupOrderDAO groupOrder, UUID menuId, Integer menuCount) {
        this.groupOrderDAO = groupOrder;
        this.menuId = menuId;
        this.menuCount = menuCount;
    }

    // 수량 증가
    public void increaseCount() {
        this.menuCount++;
    }

    // 수량 감소
    public void decreaseCount() {
        if (this.menuCount > 0) {
            this.menuCount--;
        }
    }
}
