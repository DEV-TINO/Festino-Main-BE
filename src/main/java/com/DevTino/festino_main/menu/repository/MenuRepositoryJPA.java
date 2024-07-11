package com.DevTino.festino_main.menu.repository;

import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuRepositoryJPA extends JpaRepository<MenuDAO, UUID> {
}
