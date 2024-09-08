package com.DevTino.festino_main.review.domain.entitiy;

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
public class ReviewDAO {
    @Id
    UUID reviewId;

    String content;
    String email;

    LocalDateTime createAt;
    LocalDateTime uploadAt;
}
