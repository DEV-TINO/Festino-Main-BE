package com.DevTino.festino_main.review.service;

import com.DevTino.festino_main.review.bean.SaveReviewBean;
import com.DevTino.festino_main.review.domain.DTO.RequestReviewSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewService {

    SaveReviewBean saveReviewBean;

    @Autowired
    public ReviewService(SaveReviewBean saveReviewBean) {
        this.saveReviewBean = saveReviewBean;
    }

    public UUID saveReview(RequestReviewSaveDTO requestReviewSaveDTO){
        return saveReviewBean.exec(requestReviewSaveDTO);
    }
}
