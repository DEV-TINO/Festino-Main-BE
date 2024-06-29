package com.DevTino.festino_main.bean;

import com.DevTino.festino_main.bean.small.GetFoodBoothDAOBean;
import com.DevTino.festino_main.bean.small.SaveFoodBoothDTOBean;
import com.DevTino.festino_main.domain.DTO.ResponseFoodBoothDTO;
import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFoodBoothBean {

    GetFoodBoothDAOBean getFoodBoothDAOBean;
    SaveFoodBoothDTOBean saveFoodBoothDTOBean;

    @Autowired
    public GetFoodBoothBean(GetFoodBoothDAOBean getFoodBoothDAOBean, SaveFoodBoothDTOBean saveFoodBoothDTOBean){
        this.getFoodBoothDAOBean = getFoodBoothDAOBean;
        this.saveFoodBoothDTOBean = saveFoodBoothDTOBean;
    }

    public ResponseFoodBoothDTO exec(UUID boothId){

        FoodBoothDAO foodBoothDAO = getFoodBoothDAOBean.exec(boothId);

        return saveFoodBoothDTOBean.exec(foodBoothDAO);
    }
}
