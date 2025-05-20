package com.DevTino.festino_main.review.bean.small;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.review.domain.DTO.RequestReviewSaveDTO;
import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateReviewDAOBean {
    public ReviewDAO exec(RequestReviewSaveDTO requestReviewSaveDTO){
        return ReviewDAO.builder()
                .reviewId(UUID.randomUUID())
                .rating(requestReviewSaveDTO.getRating())
                .goodFunc(requestReviewSaveDTO.getGoodFunc())
                .badFunc(requestReviewSaveDTO.getBadFunc())
                .reason(requestReviewSaveDTO.getReason())
                .reuse(requestReviewSaveDTO.getReuse())
                .feedback(requestReviewSaveDTO.getFeedback())
                .name(requestReviewSaveDTO.getName())
                .phoneNum(requestReviewSaveDTO.getPhoneNum())
                .studentNum(requestReviewSaveDTO.getStudentNum())
                .createAt(DateTimeUtils.nowZone())
                .uploadAt(DateTimeUtils.nowZone())
                .build();
    }
}
