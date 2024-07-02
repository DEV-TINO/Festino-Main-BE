package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.repository.JPADayBoothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetDayBoothDAOBean {

    JPADayBoothRepository jpaDayBoothRepository;

    @Autowired
    public GetDayBoothDAOBean(JPADayBoothRepository jpaDayBoothRepository){
        this.jpaDayBoothRepository = jpaDayBoothRepository;
    }

    // 주간 부스 가져오기
    public DayBoothDAO exec(UUID boothId){
        return jpaDayBoothRepository.findById(boothId).get();
    }
}