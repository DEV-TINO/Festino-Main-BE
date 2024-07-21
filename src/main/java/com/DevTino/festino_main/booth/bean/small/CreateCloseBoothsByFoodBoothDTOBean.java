package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateCloseBoothsByFoodBoothDTOBean {

    CreateBoothsByFoodBoothDTOBean createBoothsByFoodBoothDTOBean;

    @Autowired
    public CreateCloseBoothsByFoodBoothDTOBean(CreateBoothsByFoodBoothDTOBean createBoothsByFoodBoothDTOBean){
        this.createBoothsByFoodBoothDTOBean = createBoothsByFoodBoothDTOBean;
    }


    public List<ResponseAllBoothDTO> exec(List<FoodBoothDAO> foodBoothDAOList){

        List<ResponseAllBoothDTO> responseFoodBoothDAOList = new ArrayList<>();
        List<ResponseAllBoothDTO> responseCloseFoodBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (FoodBoothDAO foodBoothDAO : foodBoothDAOList) {

            ResponseAllBoothDTO responseAllBoothDTO = createBoothsByFoodBoothDTOBean.exec(foodBoothDAO);

            if (foodBoothDAO.getIsOpen() != true) {
                responseFoodBoothDAOList.add(responseAllBoothDTO);
            }
        }
        return responseFoodBoothDAOList;
    }
}
