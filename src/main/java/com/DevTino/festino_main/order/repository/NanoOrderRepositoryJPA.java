package com.DevTino.festino_main.order.repository;

import com.DevTino.festino_main.order.domain.NanoOrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NanoOrderRepositoryJPA extends JpaRepository<NanoOrderDAO, UUID> {
    List<NanoOrderDAO> findAllByUserNameAndPhoneNum(String userName, String phoneNum);
}
