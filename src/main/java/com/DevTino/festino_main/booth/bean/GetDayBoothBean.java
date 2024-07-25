package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.GetDayBoothDAOBean;
import com.DevTino.festino_main.booth.bean.small.CreateDayBoothDTOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothGetDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetDayBoothBean {

    GetDayBoothDAOBean getDayBoothDAOBean;
    CreateDayBoothDTOBean createDayBoothDTOBean;

    @Autowired
    public GetDayBoothBean(GetDayBoothDAOBean getDayBoothDAOBean, CreateDayBoothDTOBean createDayBoothDTOBean){
        this.getDayBoothDAOBean = getDayBoothDAOBean;
        this.createDayBoothDTOBean = createDayBoothDTOBean;
    }

    // 주간 부스 전체 조회
    public ResponseDayBoothGetDTO exec(UUID boothId){

        // 주간 부스 디테일 dao 가져오기
        DayBoothDAO dayBoothDAO = getDayBoothDAOBean.exec(boothId);
        if(dayBoothDAO == null) return null;

        // 가져온 dao를 dto로 변환
        return createDayBoothDTOBean.exec(dayBoothDAO);
    }
}