package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateFoodBoothsDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetFoodBoothsDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFoodBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFoodBoothsBean {

    GetFoodBoothsDAOBean getFoodBoothsDAOBean;
    CreateFoodBoothsDTOBean createFoodBoothsDTOBean;

    @Autowired
    public GetFoodBoothsBean(GetFoodBoothsDAOBean getFoodBoothsDAOBean, CreateFoodBoothsDTOBean createFoodBoothsDTOBean){
        this.getFoodBoothsDAOBean = getFoodBoothsDAOBean;
        this.createFoodBoothsDTOBean = createFoodBoothsDTOBean;
    }

    public List<ResponseAllFoodBoothDTO> exec(){

        // 푸드트럭 시간 순 전체 조회
        List<FoodBoothDAO> foodBoothDAOList = getFoodBoothsDAOBean.exec();

        return createFoodBoothsDTOBean.exec(foodBoothDAOList);
    }
}
