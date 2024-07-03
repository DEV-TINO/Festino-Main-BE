package com.DevTino.festino_main.order.service;

import com.DevTino.festino_main.order.bean.SaveOrderBean;
import com.DevTino.festino_main.order.model.DTO.RequestOrderSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    SaveOrderBean saveOrderBean;

    @Autowired
    public OrderService(SaveOrderBean saveOrderBean) {
        this.saveOrderBean = saveOrderBean;
    }

    // 주문 등록
    public UUID saveOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        return saveOrderBean.exec(requestOrderSaveDTO);
    }
}
