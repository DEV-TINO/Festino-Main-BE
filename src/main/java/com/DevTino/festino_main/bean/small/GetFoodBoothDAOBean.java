package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.repository.JPAFoodBoothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFoodBoothDAOBean {

    JPAFoodBoothRepository jpaFoodBoothRepository;

    @Autowired
    public GetFoodBoothDAOBean(JPAFoodBoothRepository jpaFoodBoothRepository){
        this.jpaFoodBoothRepository = jpaFoodBoothRepository;
    }

    public FoodBoothDAO exec(UUID boothId){
        return jpaFoodBoothRepository.findById(boothId).get();
    }

}
