package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.GetFoodBoothDAOBean;
import com.DevTino.festino_main.booth.bean.small.CreateFoodBoothDTOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFoodBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFoodBoothBean {

    GetFoodBoothDAOBean getFoodBoothDAOBean;
    CreateFoodBoothDTOBean createFoodBoothDTOBean;

    @Autowired
    public GetFoodBoothBean(GetFoodBoothDAOBean getFoodBoothDAOBean, CreateFoodBoothDTOBean createFoodBoothDTOBean){
        this.getFoodBoothDAOBean = getFoodBoothDAOBean;
        this.createFoodBoothDTOBean = createFoodBoothDTOBean;
    }

    // 푸드트럭 부스 전체 조회
    public ResponseFoodBoothDTO exec(UUID boothId){

        // 푸드트럭 부스 디테일 dao 가져오기
        FoodBoothDAO foodBoothDAO = getFoodBoothDAOBean.exec(boothId);
        if(foodBoothDAO == null) return null;

        // 가져온 dao를 dto로 변환
        return createFoodBoothDTOBean.exec(foodBoothDAO);
    }
}
