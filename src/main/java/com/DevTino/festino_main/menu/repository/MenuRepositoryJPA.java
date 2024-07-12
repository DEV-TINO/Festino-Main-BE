package com.DevTino.festino_main.menu.repository;

import com.DevTino.festino_main.menu.domain.entity.MenuDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuRepositoryJPA extends JpaRepository<MenuDAO, UUID> {
    List<MenuDAO> findALlByBoothId(UUID boothId);
}
