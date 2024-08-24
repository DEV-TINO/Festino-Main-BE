package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.MachinedesignOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachinedesignOrderRepositoryJPA extends JpaRepository<MachinedesignOrderDAO, Integer> {
    List<MachinedesignOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
