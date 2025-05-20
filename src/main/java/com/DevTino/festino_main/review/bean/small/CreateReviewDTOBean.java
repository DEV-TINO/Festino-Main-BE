package com.DevTino.festino_main.review.bean.small;

import com.DevTino.festino_main.review.domain.DTO.ResponseReviewGetDTO;
import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateReviewDTOBean {
    public ResponseReviewGetDTO exec(ReviewDAO reviewDAO){
        return ResponseReviewGetDTO.builder()
                .reviewId(reviewDAO.getReviewId())
                .rating(reviewDAO.getRating())
                .goodFunc(reviewDAO.getGoodFunc())
                .badFunc(reviewDAO.getBadFunc())
                .reason(reviewDAO.getReason())
                .reuse(reviewDAO.getReuse())
                .feedback(reviewDAO.getFeedback())
                .name(reviewDAO.getName())
                .phoneNum(reviewDAO.getPhoneNum())
                .studentNum(reviewDAO.getStudentNum())
                .build();
    }
}
