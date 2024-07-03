package com.DevTino.festino_main.order.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RequestOrderSaveDTO {
    Integer tableNum;
    String userName;
    String phoneNum;
    List<MenuInfoDTO> menuList;
    Integer totalPrice;
    Boolean isCoupon;
}
