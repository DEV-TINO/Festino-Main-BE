package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetDayBoothBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothDTO;
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