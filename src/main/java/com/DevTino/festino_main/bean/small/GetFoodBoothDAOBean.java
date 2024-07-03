package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.repository.FoodBoothRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFoodBoothDAOBean {

    FoodBoothRepositoryJPA foodBoothRepositoryJPA;

    @Autowired
    public GetFoodBoothDAOBean(FoodBoothRepositoryJPA foodBoothRepositoryJPA){
        this.foodBoothRepositoryJPA = foodBoothRepositoryJPA;
    }

    //푸드트럭 가져오기
    public FoodBoothDAO exec(UUID boothId){
        return foodBoothRepositoryJPA.findById(boothId).orElse(null);
    }
}
