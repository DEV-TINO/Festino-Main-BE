package com.DevTino.festino_main.group_order.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InitInfo {
    Integer memberCount;
    Integer totalPrice;
    Integer totalCount;
    Integer remainingMinutes;
    List<MenuInfo> menuList;
}
