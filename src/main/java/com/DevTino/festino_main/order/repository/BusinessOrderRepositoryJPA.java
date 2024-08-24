package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.BusinessOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessOrderRepositoryJPA extends JpaRepository<BusinessOrderDAO, Integer> {
    List<BusinessOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
