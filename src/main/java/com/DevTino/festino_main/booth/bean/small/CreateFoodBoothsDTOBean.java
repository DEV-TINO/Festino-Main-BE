package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFoodBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateFoodBoothsDTOBean {

    CreateOpenFoodBoothsDTOBean createOpenFoodBoothsDTOBean;
    CreateCloseFoodBoothsDTOBean createCloseFoodBoothsDTOBean;

    @Autowired
    public CreateFoodBoothsDTOBean(CreateOpenFoodBoothsDTOBean createOpenFoodBoothsDTOBean, CreateCloseFoodBoothsDTOBean createCloseFoodBoothsDTOBean){
        this.createOpenFoodBoothsDTOBean = createOpenFoodBoothsDTOBean;
        this.createCloseFoodBoothsDTOBean = createCloseFoodBoothsDTOBean;
    }

    public List<ResponseAllFoodBoothDTO> exec(List<FoodBoothDAO> foodBoothDAOList){
        List<ResponseAllFoodBoothDTO> responseAllFoodBoothDTOS = new ArrayList<>();

        List<ResponseAllFoodBoothDTO> responseOpenAllFoodBoothDTOList = createOpenFoodBoothsDTOBean.exec(foodBoothDAOList);
        List<ResponseAllFoodBoothDTO> responseCloseAllFoodBoothDTOList = createCloseFoodBoothsDTOBean.exec(foodBoothDAOList);

        responseAllFoodBoothDTOS.addAll(responseOpenAllFoodBoothDTOList);
        responseAllFoodBoothDTOS.addAll(responseCloseAllFoodBoothDTOList);

        return responseAllFoodBoothDTOS;
    }
}
