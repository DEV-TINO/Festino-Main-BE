package com.DevTino.festino_main.service;

import com.DevTino.festino_main.bean.GetDayBoothBean;
import com.DevTino.festino_main.domain.DTO.ResponseDayBoothDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DayBoothService {

    GetDayBoothBean getDayBoothBean;

    @Autowired
    public DayBoothService(GetDayBoothBean getDayBoothBean){
        this.getDayBoothBean = getDayBoothBean;
    }

    // 주간 부스 디테일 조회
    public ResponseDayBoothDTO getDayBooth(UUID boothId) {
        return getDayBoothBean.exec(boothId);
    }
}