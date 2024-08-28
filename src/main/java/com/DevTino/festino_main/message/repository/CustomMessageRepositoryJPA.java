package com.DevTino.festino_main.message.repository;

import com.DevTino.festino_main.message.domain.CustomMessageDAO;
import com.DevTino.festino_main.message.domain.ENUM.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomMessageRepositoryJPA extends JpaRepository<CustomMessageDAO, Long> {
    CustomMessageDAO findByBoothIdAndMessageType(UUID boothId, MessageType messageType);
}
