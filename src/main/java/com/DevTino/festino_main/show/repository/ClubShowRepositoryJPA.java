package com.DevTino.festino_main.show.repository;

import com.DevTino.festino_main.show.domain.entity.ClubShowDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClubShowRepositoryJPA extends JpaRepository<ClubShowDAO, UUID> {
}
