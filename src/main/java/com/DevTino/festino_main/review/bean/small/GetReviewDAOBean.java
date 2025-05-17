package com.DevTino.festino_main.review.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

    // reviewId로 DAO 가져오기
    public ReviewDAO exec(UUID reviewId){

        ReviewDAO dao = reviewRepositoryJPA.findById(reviewId).orElse(null);
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return dao;

    }
}
