package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFoodBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateOpenFoodBoothsDTOBean {

    CreateAllFoodBoothDTOBean createAllFoodBoothDTOBean;

    public CreateOpenFoodBoothsDTOBean(CreateAllFoodBoothDTOBean createAllFoodBoothDTOBean) {
        this.createAllFoodBoothDTOBean = createAllFoodBoothDTOBean;
    }

    public List<ResponseAllFoodBoothDTO> exec(List<FoodBoothDAO> foodBoothDAOList){

        List<ResponseAllFoodBoothDTO> responseAllFoodBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (FoodBoothDAO foodBoothDAO : foodBoothDAOList) {

            ResponseAllFoodBoothDTO responseAllFoodBoothDTO = createAllFoodBoothDTOBean.exec(foodBoothDAO);

            if (foodBoothDAO.getIsOpen() == true) {
                responseAllFoodBoothDAOList.add(responseAllFoodBoothDTO);
            }
        }
        return responseAllFoodBoothDAOList;
    }
}
