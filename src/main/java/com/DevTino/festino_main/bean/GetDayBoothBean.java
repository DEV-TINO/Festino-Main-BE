package com.DevTino.festino_main.bean;

import com.DevTino.festino_main.bean.small.GetDayBoothDAOBean;
import com.DevTino.festino_main.bean.small.CreateDayBoothDTOBean;
import com.DevTino.festino_main.domain.DTO.ResponseDayBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetDayBoothBean {

    GetDayBoothDAOBean getDayBoothDAOBean;
    CreateDayBoothDTOBean saveDayBoothDTOBean;

    @Autowired
    public GetDayBoothBean(GetDayBoothDAOBean getDayBoothDAOBean, CreateDayBoothDTOBean saveDayBoothDTOBean){
        this.getDayBoothDAOBean = getDayBoothDAOBean;
        this.saveDayBoothDTOBean = saveDayBoothDTOBean;
    }

    // 주간 부스 전체 조회
    public ResponseDayBoothDTO exec(UUID boothId){

        // 주간 부스 디테일 dao 가져오기
        DayBoothDAO dayBoothDAO = getDayBoothDAOBean.exec(boothId);
        if(dayBoothDAO == null) return null;

        // 가져온 dao를 dto로 변환
        return saveDayBoothDTOBean.exec(dayBoothDAO);
    }
}