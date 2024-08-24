package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.MachineOrderDAO;
import com.DevTino.festino_main.order.domain.NewMaterialOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachineOrderRepositoryJPA extends JpaRepository<MachineOrderDAO, Integer> {
    List<MachineOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
