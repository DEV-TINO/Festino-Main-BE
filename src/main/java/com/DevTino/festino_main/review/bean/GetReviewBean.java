package com.DevTino.festino_main.review.bean;

import com.DevTino.festino_main.review.bean.small.CreateReviewDTOBean;
import com.DevTino.festino_main.review.bean.small.GetReviewDAOBean;
import com.DevTino.festino_main.review.domain.DTO.ResponseReviewGetDTO;
import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetReviewBean {

    GetReviewDAOBean getReviewDAOBean;
    CreateReviewDTOBean createReviewDTOBean;

    @Autowired
    public GetReviewBean(GetReviewDAOBean getReviewDAOBean, CreateReviewDTOBean createReviewDTOBean){
        this.getReviewDAOBean = getReviewDAOBean;
        this.createReviewDTOBean = createReviewDTOBean;
    }

    // 리뷰 조회
    public ResponseReviewGetDTO exec(UUID reviewId){

        // reviewId로 DAO 가져오기
        ReviewDAO reviewDAO = getReviewDAOBean.exec(reviewId);
        if(reviewDAO == null) return null;

        // DTO 생성 후 반환
        return createReviewDTOBean.exec(reviewDAO);
    }
}
