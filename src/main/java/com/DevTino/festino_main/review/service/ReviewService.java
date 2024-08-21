package com.DevTino.festino_main.review.service;

import com.DevTino.festino_main.review.bean.GetReviewBean;
import com.DevTino.festino_main.review.bean.SaveReviewBean;
import com.DevTino.festino_main.review.domain.DTO.RequestReviewSaveDTO;
import com.DevTino.festino_main.review.domain.DTO.ResponseReviewGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewService {

    SaveReviewBean saveReviewBean;
    GetReviewBean getReviewBean;

    @Autowired
    public ReviewService(SaveReviewBean saveReviewBean,GetReviewBean getReviewBean) {
        this.saveReviewBean = saveReviewBean;
        this.getReviewBean = getReviewBean;
    }

    // 리뷰 조회
    public ResponseReviewGetDTO getReview(UUID reviewId){
        return getReviewBean.exec(reviewId);
    }

    // 리뷰 저장
    public UUID saveReview(RequestReviewSaveDTO requestReviewSaveDTO){
        return saveReviewBean.exec(requestReviewSaveDTO);
    }
}
