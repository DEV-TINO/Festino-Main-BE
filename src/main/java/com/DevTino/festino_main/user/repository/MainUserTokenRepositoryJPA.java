package com.DevTino.festino_main.user.repository;

import com.DevTino.festino_main.user.domain.entity.MainUserTokenDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MainUserTokenRepositoryJPA extends JpaRepository<MainUserTokenDAO, UUID> {
}
