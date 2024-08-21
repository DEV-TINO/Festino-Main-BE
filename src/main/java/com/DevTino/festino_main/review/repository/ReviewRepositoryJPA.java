package com.DevTino.festino_main.review.repository;

import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepositoryJPA extends JpaRepository<ReviewDAO, UUID> {
}
