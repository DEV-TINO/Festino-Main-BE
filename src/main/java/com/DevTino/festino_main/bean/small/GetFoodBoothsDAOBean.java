package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.repository.FoodBoothRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFoodBoothsDAOBean {

    FoodBoothRepositoryJPA foodBoothRepositoryJPA;

    @Autowired
    public GetFoodBoothsDAOBean(FoodBoothRepositoryJPA foodBoothRepositoryJPA){
        this.foodBoothRepositoryJPA = foodBoothRepositoryJPA;
    }

    // 푸드트럭 부스 리스트 가져오기
    public List<FoodBoothDAO> exec(){
        return foodBoothRepositoryJPA.findAll();
    }
}