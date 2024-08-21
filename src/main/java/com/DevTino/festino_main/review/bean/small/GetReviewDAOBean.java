package com.DevTino.festino_main.review.bean.small;

import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import com.DevTino.festino_main.review.repository.ReviewRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetReviewDAOBean {

    ReviewRepositoryJPA reviewRepositoryJPA;

    @Autowired
    public GetReviewDAOBean(ReviewRepositoryJPA reviewRepositoryJPA){
        this.reviewRepositoryJPA = reviewRepositoryJPA;
    }

    public ReviewDAO exec(UUID reviewId){
        return reviewRepositoryJPA.findById(reviewId).orElse(null);
    }
}
