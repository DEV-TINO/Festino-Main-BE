package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.ComputerOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ComputerOrderRepositoryJPA extends JpaRepository<ComputerOrderDAO, UUID> {
    List<ComputerOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
