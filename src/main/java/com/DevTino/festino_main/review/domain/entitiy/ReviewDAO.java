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

    Integer rating;
    String goodFunc;
    String badFunc;
    String reason;
    String reuse;
    String feedback;
    String name;
    String phoneNum;
    String studentNum;

    LocalDateTime createAt;
    LocalDateTime uploadAt;
}
