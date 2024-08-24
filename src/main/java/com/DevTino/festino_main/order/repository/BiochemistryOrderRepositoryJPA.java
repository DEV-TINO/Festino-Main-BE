package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.BiochemistryOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiochemistryOrderRepositoryJPA extends JpaRepository<BiochemistryOrderDAO, Integer> {
    List<BiochemistryOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
