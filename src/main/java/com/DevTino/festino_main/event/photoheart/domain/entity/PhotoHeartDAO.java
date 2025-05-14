package com.DevTino.festino_main.event.photoheart.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoHeartDAO {
    @Id
    private UUID photoHeartId;
    private UUID mainUserId;
    private UUID photoId;

    private LocalDateTime createAt;
}
