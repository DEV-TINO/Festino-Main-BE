package com.DevTino.festino_main.event.real.repository;

import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RealTimeRepositoryJPA extends JpaRepository<RealTimeQuestionDAO, UUID> {

    RealTimeQuestionDAO findByIsOpenTrue();
}
