package com.DevTino.festino_main.review.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import com.DevTino.festino_main.review.repository.ReviewRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReviewsDAOBean {

    ReviewRepositoryJPA reviewRepositoryJPA;

    @Autowired
    public GetReviewsDAOBean(ReviewRepositoryJPA reviewRepositoryJPA){
        this.reviewRepositoryJPA = reviewRepositoryJPA;
    }

    // 리뷰 전체 리스트로 가져오기
    public List<ReviewDAO> exec(){

        List<ReviewDAO> daoList = reviewRepositoryJPA.findAll();
        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;

    }
}
