package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.EnergyOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnergyOrderRepositoryJPA extends JpaRepository<EnergyOrderDAO, Integer> {
    List<EnergyOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
