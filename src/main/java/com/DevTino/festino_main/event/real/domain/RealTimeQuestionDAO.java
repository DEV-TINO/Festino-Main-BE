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
public class RealTimeQuestionDAO {
    @Id
    UUID realTimeQuestionId;

    String question;

    Boolean isOpen;

    LocalDateTime startTime;
    LocalDateTime endTime;

    LocalDateTime createAt;
    LocalDateTime updateAt;
}
