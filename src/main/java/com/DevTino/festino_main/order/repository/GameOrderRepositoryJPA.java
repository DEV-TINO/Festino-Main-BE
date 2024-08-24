package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.GameOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GameOrderRepositoryJPA extends JpaRepository<GameOrderDAO, UUID> {
    List<GameOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
