package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.model.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.model.OrderDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateOrderDAOBean {

    // requestOrderSaveDTO -> OrderDAO 변경
    public OrderDAO exec(RequestOrderSaveDTO requestOrderSaveDTO) {
        return OrderDAO.builder()
                .orderId(UUID.randomUUID())
                .tableNum(requestOrderSaveDTO.getTableNum())
                .userName(requestOrderSaveDTO.getUserName())
                .phoneNum(requestOrderSaveDTO.getPhoneNum())
                .menuList(requestOrderSaveDTO.getMenuInfo())
                .totalPrice(requestOrderSaveDTO.getTotalPrice())
                .createAt(LocalDateTime.now())
                .isCoupon(requestOrderSaveDTO.getIsCoupon())
                .isDeleted(false)
                .build();
    }
}
