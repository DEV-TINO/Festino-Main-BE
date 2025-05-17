package com.DevTino.festino_main.review.bean;

import com.DevTino.festino_main.review.bean.small.CreateReviewsDTOBean;
import com.DevTino.festino_main.review.bean.small.GetReviewsDAOBean;
import com.DevTino.festino_main.review.domain.DTO.ResponseReviewGetDTO;
import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReviewsBean {

    GetReviewsDAOBean getReviewsDAOBean;
    CreateReviewsDTOBean createReviewsDTOBean;

    @Autowired
    public GetReviewsBean(GetReviewsDAOBean getReviewsDAOBean, CreateReviewsDTOBean createReviewsDTOBean){
        this.getReviewsDAOBean = getReviewsDAOBean;
        this.createReviewsDTOBean = createReviewsDTOBean;
    }

    // 리뷰 전체 조회
    public List<ResponseReviewGetDTO> exec(){

        // 리뷰 전체 리스트로 가져오기
        List<ReviewDAO> reviewDAOList = getReviewsDAOBean.exec();

        //
        return createReviewsDTOBean.exec(reviewDAOList);
    }
}
