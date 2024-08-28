package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.DesignOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignOrderRepositoryJPA extends JpaRepository<DesignOrderDAO, Integer> {
    List<DesignOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
