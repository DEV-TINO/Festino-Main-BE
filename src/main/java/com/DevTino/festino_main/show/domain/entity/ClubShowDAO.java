package com.DevTino.festino_main.show.domain.entity;

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
public class ClubShowDAO {
    @Id
    UUID talentId;

    String clubName;
    String showData;
    String showStartTime;
    String showEndTime;
    String clubImage;

    Boolean isShowing;

    LocalDateTime createAt;
    LocalDateTime updateAt;

}
