package com.DevTino.festino_main.event.photo.repository;

import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepositoryJPA extends JpaRepository<PhotoDAO, UUID> {
}
