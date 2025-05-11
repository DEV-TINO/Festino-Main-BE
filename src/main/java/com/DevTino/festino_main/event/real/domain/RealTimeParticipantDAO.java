package com.DevTino.festino_main.event.real.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RealTimeParticipantDAO {
    @Id
    UUID realTimeParticipantId;

    UUID mainUserId;
    UUID realTimeQuestionId;

    String answer;

    LocalDateTime createAt;
    LocalDateTime updateAt;
}
