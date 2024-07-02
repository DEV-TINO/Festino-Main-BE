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

    // 푸드트럭 부스 dao 가져온 후 dto 반환
    public ResponseFoodBoothDTO exec(UUID boothId){

        // 푸드트럭 부스 디테일 dao 가져오기
        FoodBoothDAO foodBoothDAO = getFoodBoothDAOBean.exec(boothId);

        // 가져온 dao를 dto로 변환
        return saveFoodBoothDTOBean.exec(foodBoothDAO);
    }
}
