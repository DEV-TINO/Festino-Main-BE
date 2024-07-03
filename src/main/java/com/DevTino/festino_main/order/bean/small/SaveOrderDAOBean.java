package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.model.OrderDAO;
import com.DevTino.festino_main.order.repository.OrderRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveOrderDAOBean {
    OrderRepositoryJPA orderRepositoryJPA;

    @Autowired
    public SaveOrderDAOBean(OrderRepositoryJPA orderRepositoryJPA) {
        this.orderRepositoryJPA = orderRepositoryJPA;
    }

    // 주문 등록
    public void exec(OrderDAO orderDAO) {
        orderRepositoryJPA.save(orderDAO);
    }
}
