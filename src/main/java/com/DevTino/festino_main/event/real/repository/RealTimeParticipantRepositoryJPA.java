package com.DevTino.festino_main.event.real.repository;

import com.DevTino.festino_main.event.real.domain.RealTimeParticipantDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RealTimeParticipantRepositoryJPA extends JpaRepository<RealTimeParticipantDAO, UUID> {

    boolean existsByMainUserIdAndRealTimeQuestionId(UUID mainUserId, UUID realTimeId);
}
