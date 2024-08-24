package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.ElectronicsOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectronicsOrderRepositoryJPA extends JpaRepository<ElectronicsOrderDAO, Integer> {
    List<ElectronicsOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
