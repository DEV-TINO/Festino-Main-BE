package com.DevTino.festino_main.repository;

import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DayBoothRepositoryJPA extends JpaRepository<DayBoothDAO, UUID> {
}