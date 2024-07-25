package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseFoodBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateFoodBoothsDTOBean {

    CreateAllFoodBoothDTOBean createAllFoodBoothDTOBean;

    @Autowired
    public CreateFoodBoothsDTOBean(CreateAllFoodBoothDTOBean createAllFoodBoothDTOBean) {
        this.createAllFoodBoothDTOBean = createAllFoodBoothDTOBean;
    }

    // 푸드트럭 오픈 중과 오픈 아닌 것으로 나누어 정렬
    public List<ResponseFoodBoothsGetDTO> exec(List<FoodBoothDAO> foodBoothDAOList){

        List<ResponseFoodBoothsGetDTO> responseFoodBoothsGetDTOList = new ArrayList<>();

        List<ResponseFoodBoothsGetDTO> responseOpenAllFoodBoothDTOList = new ArrayList<>();
        List<ResponseFoodBoothsGetDTO> responseCloseAllFoodBoothDTOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (FoodBoothDAO foodBoothDAO : foodBoothDAOList) {

            ResponseFoodBoothsGetDTO responseFoodBoothsGetDTO = createAllFoodBoothDTOBean.exec(foodBoothDAO);

            if (foodBoothDAO.getIsOpen())
                responseOpenAllFoodBoothDTOList.add(responseFoodBoothsGetDTO);
            else
                responseCloseAllFoodBoothDTOList.add(responseFoodBoothsGetDTO);

        }

        responseFoodBoothsGetDTOList.addAll(responseOpenAllFoodBoothDTOList);
        responseFoodBoothsGetDTOList.addAll(responseCloseAllFoodBoothDTOList);

        return responseFoodBoothsGetDTOList;
    }
}
