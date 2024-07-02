package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.repository.JPAFoodBoothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllFoodBoothDAOBean {

    JPAFoodBoothRepository jpaFoodBoothRepository;

    @Autowired
    public GetAllFoodBoothDAOBean(JPAFoodBoothRepository jpaFoodBoothRepository){
        this.jpaFoodBoothRepository = jpaFoodBoothRepository;
    }

    // 푸드트럭 부스 리스트 가져오기
    public List<FoodBoothDAO> exec(){
        return jpaFoodBoothRepository.findAll();
    }
}