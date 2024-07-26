package com.DevTino.festino_main.order.bean.small;

import com.DevTino.festino_main.order.domain.OrderDAO;
import com.DevTino.festino_main.order.repository.OrderRepositoryJPA;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetOrdersDAOBean {

    OrderRepositoryJPA orderRepositoryJPA;

    public GetOrdersDAOBean(OrderRepositoryJPA orderRepositoryJPA) {
        this.orderRepositoryJPA = orderRepositoryJPA;
    }

    // 유저의 핸드폰 번호로 입금 완료된 주문 내역들을 조회
    @Transactional(readOnly = true)
    public List<OrderDAO> exec(String phoneNum) {
        return orderRepositoryJPA.findAllByPhoneNumOrderByCreateAtDesc(phoneNum);
    }
}
