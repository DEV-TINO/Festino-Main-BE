package com.DevTino.festino_main.repository;

import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NightBoothRepositoryJPA extends JpaRepository<NightBoothDAO, UUID> {
}
