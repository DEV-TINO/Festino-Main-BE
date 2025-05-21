package com.DevTino.festino_main.review.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseReviewGetDTO {
    UUID reviewId;

    Integer rating;
    List<String> goodFunc;
    List<String> badFunc;
    String reason;
    String reuse;
    String feedback;
    String name;
    String phoneNum;
    String studentNum;
}
