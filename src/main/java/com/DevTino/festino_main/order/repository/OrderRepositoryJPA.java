package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepositoryJPA extends JpaRepository<OrderDTO, UUID> {
    List<OrderDTO> findAllByPhoneNumOrderByCreateAtDesc(String phoneNum);
}
