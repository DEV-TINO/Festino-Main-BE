package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.OrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepositoryJPA extends JpaRepository<OrderDAO, UUID> {
    List<OrderDAO> findAllByUserNameAndPhoneNumOrderByCreateAtDesc(String userName, String phoneNum);

    OrderDAO findFirstByDateOrderByOrderNumDesc(Integer date);
}
