package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFoodBoothDTO;
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

    public List<ResponseAllFoodBoothDTO> exec(List<FoodBoothDAO> foodBoothDAOList){

        List<ResponseAllFoodBoothDTO> responseAllFoodBoothDTOList = new ArrayList<>();

        List<ResponseAllFoodBoothDTO> responseOpenAllFoodBoothDTOList = new ArrayList<>();
        List<ResponseAllFoodBoothDTO> responseCloseAllFoodBoothDTOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (FoodBoothDAO foodBoothDAO : foodBoothDAOList) {

            ResponseAllFoodBoothDTO responseAllFoodBoothDTO = createAllFoodBoothDTOBean.exec(foodBoothDAO);

            if (foodBoothDAO.getIsOpen())
                responseOpenAllFoodBoothDTOList.add(responseAllFoodBoothDTO);
            else
                responseCloseAllFoodBoothDTOList.add(responseAllFoodBoothDTO);

        }

        responseAllFoodBoothDTOList.addAll(responseOpenAllFoodBoothDTOList);
        responseAllFoodBoothDTOList.addAll(responseCloseAllFoodBoothDTOList);

        return responseAllFoodBoothDTOList;
    }
}
