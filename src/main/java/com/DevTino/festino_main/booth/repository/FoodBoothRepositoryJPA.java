package com.DevTino.festino_main.booth.repository;

import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FoodBoothRepositoryJPA extends JpaRepository<FoodBoothDAO, UUID> {
}