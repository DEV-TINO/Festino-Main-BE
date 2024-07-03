package com.DevTino.festino_main.repository;

import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FoodBoothRepositoryJPA extends JpaRepository<FoodBoothDAO, UUID> {
}