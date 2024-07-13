package com.DevTino.festino_main.show.repository;

import com.DevTino.festino_main.show.domain.entity.TalentShowDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TalentShowRepositoryJPA extends JpaRepository<TalentShowDAO, UUID> {
}
