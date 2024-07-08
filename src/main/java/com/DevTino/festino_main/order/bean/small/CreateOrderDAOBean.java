package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.domain.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.domain.OrderDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateOrderDAOBean {

    // requestOrderSaveDTO -> OrderDAO 변경
    public OrderDAO exec(RequestOrderSaveDTO requestOrderSaveDTO) {
        return OrderDAO.builder()
                .orderId(UUID.randomUUID())
                .boothId(requestOrderSaveDTO.getBoothId())
                .tableNum(requestOrderSaveDTO.getTableNum())
                .userName(requestOrderSaveDTO.getUserName())
                .phoneNum(requestOrderSaveDTO.getPhoneNum())
                .menuInfo(requestOrderSaveDTO.getMenuInfo())
                .totalPrice(requestOrderSaveDTO.getTotalPrice())
                .createAt(LocalDateTime.now())
                .isCoupon(requestOrderSaveDTO.getIsCoupon())
                .build();
    }
}
