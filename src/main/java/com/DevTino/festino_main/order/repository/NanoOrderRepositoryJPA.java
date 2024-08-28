package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.NanoOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NanoOrderRepositoryJPA extends JpaRepository<NanoOrderDAO, Integer> {
    List<NanoOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
