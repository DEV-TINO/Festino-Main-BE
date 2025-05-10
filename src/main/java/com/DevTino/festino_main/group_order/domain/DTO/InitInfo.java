package com.DevTino.festino_main.group_order.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InitInfo {
    UUID boothId;
    Integer tableNum;
    Integer memberCount;
    Integer totalPrice;
    Integer totalCount;
    List<MenuInfo> menuList;
}
