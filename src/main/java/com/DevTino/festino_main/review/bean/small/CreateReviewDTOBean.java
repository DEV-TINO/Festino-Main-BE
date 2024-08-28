package com.DevTino.festino_main.review.bean.small;

import com.DevTino.festino_main.review.domain.DTO.ResponseReviewGetDTO;
import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateReviewDTOBean {
    public ResponseReviewGetDTO exec(ReviewDAO reviewDAO){
        return ResponseReviewGetDTO.builder()
                .reviewId(reviewDAO.getReviewId())
                .email(reviewDAO.getEmail())
                .content(reviewDAO.getContent())
                .build();
    }
}
