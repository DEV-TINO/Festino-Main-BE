package com.DevTino.festino_main.review.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RequestReviewSaveDTO {
    UUID reviewId;
    String content;
    String email;
    Boolean isAnonymous;
}
