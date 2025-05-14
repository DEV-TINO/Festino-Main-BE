package com.DevTino.festino_main.event.photoheart.repository;

import com.DevTino.festino_main.event.photoheart.domain.entity.PhotoHeartDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoHeartRepositoryJPA extends JpaRepository<PhotoHeartDAO, UUID> {

    PhotoHeartDAO findByPhotoIdAndMainUserId(UUID photoId, UUID mainUserId);
}
