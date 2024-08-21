package com.DevTino.festino_main.review.bean;

import com.DevTino.festino_main.review.bean.small.CreateReviewDAOBean;
import com.DevTino.festino_main.review.bean.small.SaveReviewDAOBean;
import com.DevTino.festino_main.review.domain.DTO.RequestReviewSaveDTO;
import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveReviewBean {

    CreateReviewDAOBean createReviewDAOBean;
    SaveReviewDAOBean saveReviewDAOBean;

    @Autowired
    public SaveReviewBean(CreateReviewDAOBean createReviewDAOBean, SaveReviewDAOBean saveReviewDAOBean){
        this.createReviewDAOBean = createReviewDAOBean;
        this.saveReviewDAOBean = saveReviewDAOBean;
    }

    // 리뷰 저장
    public UUID exec(RequestReviewSaveDTO requestReviewSaveDTO){

        // 리뷰 DAO 생성
        ReviewDAO reviewDAO = createReviewDAOBean.exec(requestReviewSaveDTO);
        if (reviewDAO == null) return null;

        // DAO 저장
        saveReviewDAOBean.exec(reviewDAO);

        // reviewID 반환
        return reviewDAO.getReviewId();
    }
}
