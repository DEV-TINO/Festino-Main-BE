package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.booth.repository.DayBoothRepositoryJPA;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

        DayBoothDAO dao = dayBoothRepositoryJPA.findById(boothId).orElse(null);
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return dao;

    }
}