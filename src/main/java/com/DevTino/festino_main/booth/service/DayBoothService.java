package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetDayBoothBean;
import com.DevTino.festino_main.booth.bean.GetDayBoothsBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllDayBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DayBoothService {

    GetDayBoothBean getDayBoothBean;
    GetDayBoothsBean getDayBoothsBean;

    @Autowired
    public DayBoothService(GetDayBoothBean getDayBoothBean, GetDayBoothsBean getDayBoothsBean){
        this.getDayBoothBean = getDayBoothBean;
        this.getDayBoothsBean = getDayBoothsBean;
    }

    // 주간 부스 디테일 조회
    public ResponseDayBoothDTO getDayBooth(UUID boothId) {
        return getDayBoothBean.exec(boothId);
    }

    // 주간 부스 전체 조회
    public List<ResponseAllDayBoothDTO> getDayBooths(){
        return getDayBoothsBean.exec();
    }
}