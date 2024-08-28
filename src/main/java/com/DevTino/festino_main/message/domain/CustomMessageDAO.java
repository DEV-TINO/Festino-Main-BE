package com.DevTino.festino_main.message.domain;

import com.DevTino.festino_main.message.domain.ENUM.MessageType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomMessageDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long messageId;
    UUID boothId;
    String message;
    MessageType messageType;
}
