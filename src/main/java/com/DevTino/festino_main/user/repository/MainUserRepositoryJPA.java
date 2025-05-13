package com.DevTino.festino_main.user.repository;

import com.DevTino.festino_main.user.domain.entity.MainUserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MainUserRepositoryJPA extends JpaRepository<MainUserDAO, UUID> {

    MainUserDAO findByMainUserId(UUID mainUserId);
    MainUserDAO findByPhoneNumAndMainUserName(String phoneNum, String mainUserName);
}
