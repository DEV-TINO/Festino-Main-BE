package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetFoodBoothBean;
import com.DevTino.festino_main.booth.bean.GetFoodBoothsBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFoodBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFoodBoothDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FoodBoothService {

    GetFoodBoothBean getFoodBoothBean;
    GetFoodBoothsBean getFoodBoothsBean;

    @Autowired
    public FoodBoothService(GetFoodBoothBean getFoodBoothBean, GetFoodBoothsBean getFoodBoothsBean){
        this.getFoodBoothBean = getFoodBoothBean;
        this.getFoodBoothsBean = getFoodBoothsBean;
    }

    // 푸드트럭 디테일 조회
    public ResponseFoodBoothDTO getFoodBooth(UUID boothId){
        return getFoodBoothBean.exec(boothId);
    }

    // 푸드트럭 전체 조회
    public List<ResponseAllFoodBoothDTO> getFoodBooths(){
        return getFoodBoothsBean.exec();
    }
}