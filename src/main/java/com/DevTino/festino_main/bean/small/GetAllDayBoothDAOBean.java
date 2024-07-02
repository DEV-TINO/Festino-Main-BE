package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.repository.JPADayBoothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllDayBoothDAOBean {

    JPADayBoothRepository jpaDayBoothRepository;

    @Autowired
    public GetAllDayBoothDAOBean(JPADayBoothRepository jpaDayBoothRepository){
        this.jpaDayBoothRepository = jpaDayBoothRepository;
    }

    public List<DayBoothDAO> exec(){
        return jpaDayBoothRepository.findAll();
    }
}