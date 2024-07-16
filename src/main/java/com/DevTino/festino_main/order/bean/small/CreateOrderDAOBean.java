package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.domain.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.domain.OrderDAO;
import com.DevTino.festino_main.order.repository.OrderRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateOrderDAOBean {
    CheckOrderDAODateFieldBean checkOrderDAODateFieldBean;
    OrderRepositoryJPA orderRepositoryJPA;

    @Autowired
    public CreateOrderDAOBean(CheckOrderDAODateFieldBean checkOrderDAODateFieldBean, OrderRepositoryJPA orderRepositoryJPA) {
        this.checkOrderDAODateFieldBean = checkOrderDAODateFieldBean;
        this.orderRepositoryJPA = orderRepositoryJPA;
    }

    // requestOrderSaveDTO -> OrderDAO 변경
    public OrderDAO exec(RequestOrderSaveDTO requestOrderSaveDTO) {
        Integer date = checkOrderDAODateFieldBean.exec(requestOrderSaveDTO.getBoothId());
        OrderDAO orderDAO = orderRepositoryJPA.findFirstByDateOrderByOrderNumDesc(date);
        Integer orderNum = orderDAO == null ? 1 : orderDAO.getOrderNum() + 1;

        return OrderDAO.builder()
                .orderId(UUID.randomUUID())
                .boothId(requestOrderSaveDTO.getBoothId())
                .tableNum(requestOrderSaveDTO.getTableNum())
                .userName(requestOrderSaveDTO.getUserName())
                .phoneNum(requestOrderSaveDTO.getPhoneNum())
                .date(date)
                .orderNum(orderNum)
                .menuInfo(requestOrderSaveDTO.getMenuInfo())
                .totalPrice(requestOrderSaveDTO.getTotalPrice())
                .createAt(LocalDateTime.now())
                .isCoupon(requestOrderSaveDTO.getIsCoupon())
                .isDeposit(false)
                .build();
    }
}
