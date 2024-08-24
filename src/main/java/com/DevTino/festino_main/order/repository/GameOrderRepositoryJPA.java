package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.GameOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameOrderRepositoryJPA extends JpaRepository<GameOrderDAO, Integer> {
    List<GameOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
