package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.MechatronicsOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MechatronicsOrderRepositoryJPA extends JpaRepository<MechatronicsOrderDAO, Integer> {
    List<MechatronicsOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
