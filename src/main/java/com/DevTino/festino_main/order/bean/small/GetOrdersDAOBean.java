package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.model.OrderDAO;
import com.DevTino.festino_main.order.repository.OrderRepositoryJPA;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOrdersDAOBean {
    OrderRepositoryJPA orderRepositoryJPA;

    public GetOrdersDAOBean(OrderRepositoryJPA orderRepositoryJPA) {
        this.orderRepositoryJPA = orderRepositoryJPA;
    }

    // 유저의 이름과 핸드폰 번호로 주문 내역들을 조회
    public List<OrderDAO> exec(String userName, String phoneNum) {
        return orderRepositoryJPA.findAllByUserNameAndPhoneNumAndIsDeletedOrderByCreateAtDesc(userName, phoneNum, false);
    }
}
