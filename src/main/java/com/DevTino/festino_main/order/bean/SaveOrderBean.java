package com.DevTino.festino_main.order.bean;

import com.DevTino.festino_main.order.bean.small.CreateOrderDAOBean;
import com.DevTino.festino_main.order.bean.small.SaveOrderDAOBean;
import com.DevTino.festino_main.order.model.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.model.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveOrderBean {
    CreateOrderDAOBean createOrderDAOBean;
    SaveOrderDAOBean saveOrderDAOBean;

    @Autowired
    public SaveOrderBean(CreateOrderDAOBean createOrderDAOBean, SaveOrderDAOBean saveOrderDAOBean) {
        this.createOrderDAOBean = createOrderDAOBean;
        this.saveOrderDAOBean = saveOrderDAOBean;
    }

    // 주문 등록
    public UUID exec(RequestOrderSaveDTO requestOrderSaveDTO) {
        OrderDAO orderDAO = createOrderDAOBean.exec(requestOrderSaveDTO);
        saveOrderDAOBean.exec(orderDAO);

        return orderDAO.getOrderId();
    }
}
