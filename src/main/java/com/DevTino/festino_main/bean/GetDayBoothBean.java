package com.DevTino.festino_main.bean;

import com.DevTino.festino_main.bean.small.GetDayBoothDAOBean;
import com.DevTino.festino_main.bean.small.SaveDayBoothDTOBean;
import com.DevTino.festino_main.domain.DTO.ResponseDayBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetDayBoothBean {

    GetDayBoothDAOBean getDayBoothDAOBean;
    SaveDayBoothDTOBean saveDayBoothDTOBean;

    @Autowired
    public GetDayBoothBean(GetDayBoothDAOBean getDayBoothDAOBean, SaveDayBoothDTOBean saveDayBoothDTOBean){
        this.getDayBoothDAOBean = getDayBoothDAOBean;
        this.saveDayBoothDTOBean = saveDayBoothDTOBean;
    }

    public ResponseDayBoothDTO exec(UUID boothId){
        DayBoothDAO dayBoothDAO = getDayBoothDAOBean.exec(boothId);

        return saveDayBoothDTOBean.exec(dayBoothDAO);
    }

}