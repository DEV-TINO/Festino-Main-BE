package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.ComputerOrderDAO;
import com.DevTino.festino_main.order.domain.DTO.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ComputerOrderRepositoryJPA extends JpaRepository<ComputerOrderDAO, UUID> {
    List<OrderDTO> findAllByPhoneNum(String phoneNum);
}
