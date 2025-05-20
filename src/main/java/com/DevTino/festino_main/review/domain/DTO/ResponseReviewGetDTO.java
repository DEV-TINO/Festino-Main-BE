package com.DevTino.festino_main.review.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseReviewGetDTO {
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
}
