package com.DevTino.festino_main.order.service;

import com.DevTino.festino_main.order.bean.GetOrderBean;
import com.DevTino.festino_main.order.bean.SaveOrderBean;
import com.DevTino.festino_main.order.domain.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.domain.DTO.ResponseOrderGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    SaveOrderBean saveOrderBean;
    GetOrderBean getOrderBean;

    @Autowired
    public OrderService(SaveOrderBean saveOrderBean, GetOrderBean getOrderBean) {
        this.saveOrderBean = saveOrderBean;
        this.getOrderBean = getOrderBean;
    }

    // 주문 등록
    public UUID saveOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        return saveOrderBean.exec(requestOrderSaveDTO);
    }

    // 주문 조회
    public List<ResponseOrderGetDTO> getOrder(String userName, String phoneNum) {
        return getOrderBean.exec(userName, phoneNum);
    }
}
