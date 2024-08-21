package com.DevTino.festino_main.review.bean.small;

import com.DevTino.festino_main.review.domain.DTO.ResponseReviewGetDTO;
import com.DevTino.festino_main.review.domain.entitiy.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateReviewsDTOBean {

    CreateReviewDTOBean createReviewDTOBean;

    @Autowired
    public CreateReviewsDTOBean(CreateReviewDTOBean createReviewDTOBean){
        this.createReviewDTOBean = createReviewDTOBean;
    }

    public List<ResponseReviewGetDTO> exec(List<ReviewDAO> reviewDAOList){

        List<ResponseReviewGetDTO> responseReviewGetDTOList = new ArrayList<>();

        for (ReviewDAO reviewDAO : reviewDAOList){

            ResponseReviewGetDTO responseReviewGetDTO = createReviewDTOBean.exec(reviewDAO);

            responseReviewGetDTOList.add(responseReviewGetDTO);
        }

        return responseReviewGetDTOList;
    }

}
