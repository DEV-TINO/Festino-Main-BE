package com.DevTino.festino_main.review.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseReviewGetDTO {
    UUID reviewId;
    String content;
    String email;
}
