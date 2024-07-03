package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.repository.DayBoothRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetDayBoothDAOBean {

    DayBoothRepositoryJPA dayBoothRepositoryJPA;

    @Autowired
    public GetDayBoothDAOBean(DayBoothRepositoryJPA dayBoothRepositoryJPA){
        this.dayBoothRepositoryJPA = dayBoothRepositoryJPA;
    }

    // 주간 부스 가져오기
    public DayBoothDAO exec(UUID boothId){
        return dayBoothRepositoryJPA.findById(boothId).orElse(null);

    }
}