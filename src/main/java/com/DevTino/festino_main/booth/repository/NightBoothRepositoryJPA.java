package com.DevTino.festino_main.booth.repository;

import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NightBoothRepositoryJPA extends JpaRepository<NightBoothDAO, UUID> {
}