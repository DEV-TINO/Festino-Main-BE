package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.NewMaterialOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewMaterialOrderRepositoryJPA extends JpaRepository<NewMaterialOrderDAO, Integer> {
    List<NewMaterialOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
