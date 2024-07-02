package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.FoodBoothDAO;
import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.repository.JPAFoodBoothRepository;
import com.DevTino.festino_main.repository.JPANightBoothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllNightBoothDAOBean {

    JPANightBoothRepository jpaNightBoothRepository;

    @Autowired
    public GetAllNightBoothDAOBean(JPANightBoothRepository jpaNightBoothRepository){
        this.jpaNightBoothRepository = jpaNightBoothRepository;
    }

    public List<NightBoothDAO> exec(){
        return jpaNightBoothRepository.findAll();
    }
}