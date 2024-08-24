package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.NewMaterialOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NewMaterialOrderRepositoryJPA extends JpaRepository<NewMaterialOrderDAO, UUID> {
    List<NewMaterialOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
