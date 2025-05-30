package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.booth.repository.FoodBoothRepositoryJPA;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

        List<FoodBoothDAO> daoList = foodBoothRepositoryJPA.findAllByOrderByCreateAtAsc();
        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;

    }
}