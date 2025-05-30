package com.DevTino.festino_main.review.bean.small;

import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import com.DevTino.festino_main.review.repository.ReviewRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveReviewDAOBean {

    ReviewRepositoryJPA reviewRepositoryJPA;

    @Autowired
    public SaveReviewDAOBean(ReviewRepositoryJPA reviewRepositoryJPA){
        this.reviewRepositoryJPA = reviewRepositoryJPA;
    }

    // 리뷰 DAO를 DB에 저장
    public void exec(ReviewDAO reviewDAO){
        reviewRepositoryJPA.save(reviewDAO);
    }
}
