package com.DevTino.festino_main.review.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestReviewSaveDTO {
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
