package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.order.domain.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
import com.DevTino.festino_main.order.domain.OrderType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateOrderDAOBean {

    // requestOrderSaveDTO -> OrderDAO 변경
    public OrderDTO exec(Integer date, RequestOrderSaveDTO requestOrderSaveDTO) {

        return OrderDTO.builder()
                .orderId(UUID.randomUUID())
                .boothId(requestOrderSaveDTO.getBoothId())
                .tableNum(requestOrderSaveDTO.getTableNum())
                .userName(requestOrderSaveDTO.getUserName())
                .phoneNum(requestOrderSaveDTO.getPhoneNum())
                .date(date)
                .orderType(OrderType.COOKING)
                .menuInfo(requestOrderSaveDTO.getMenuInfo())
                .totalPrice(requestOrderSaveDTO.getTotalPrice())
                .createAt(DateTimeUtils.nowZone())
                .isCoupon(requestOrderSaveDTO.getIsCoupon())
                .isDeposit(false)
                .isService(false)
                .build();
    }
}
