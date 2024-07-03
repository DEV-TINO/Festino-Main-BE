package com.DevTino.festino_main.service;

import com.DevTino.festino_main.bean.GetFoodBoothBean;
import com.DevTino.festino_main.domain.DTO.ResponseFoodBoothDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FoodBoothService {

    GetFoodBoothBean getFoodBoothBean;

    @Autowired
    public FoodBoothService(GetFoodBoothBean getFoodBoothBean){
        this.getFoodBoothBean = getFoodBoothBean;
    }

    // 푸드트럭 디테일 조회
    public ResponseFoodBoothDTO getFoodBooth(UUID boothId){
        return getFoodBoothBean.exec(boothId);
    }
}