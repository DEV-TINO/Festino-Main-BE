package com.DevTino.festino_main.group_order.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfo {
    UUID menuId;
    Integer menuCount;
    Integer totalPrice;
    Integer totalCount;
}
