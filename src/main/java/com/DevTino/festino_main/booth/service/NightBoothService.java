package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetNightBoothBean;
import com.DevTino.festino_main.booth.bean.GetNightBoothsBean;
import com.DevTino.festino_main.booth.bean.GetReservationNightBoothsBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllNightBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseReservationNightBoothDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NightBoothService {

    GetNightBoothBean getNightBoothBean;
    GetReservationNightBoothsBean getReservationNightBoothsBean;
    GetNightBoothsBean getNightBoothsBean;

    @Autowired
    public NightBoothService(GetNightBoothBean getNightBoothBean, GetReservationNightBoothsBean getReservationNightBoothsBean, GetNightBoothsBean getNightBoothsBean){
        this.getNightBoothBean = getNightBoothBean;
        this.getReservationNightBoothsBean = getReservationNightBoothsBean;
        this.getNightBoothsBean = getNightBoothsBean;
    }

    // 야간 부스 디테일 조회
    public ResponseNightBoothDTO getNightBooth(UUID boothId){
        return getNightBoothBean.exec(boothId);
    }

    // 예약 시 야간 부스 전체 조회
    public List<ResponseReservationNightBoothDTO> getReservationNightBooths(){
        return getReservationNightBoothsBean.exec();
    }

    // 야간 부스 전체 조회
    public List<ResponseAllNightBoothDTO> getNightBooths(){
        return getNightBoothsBean.exec();
    }
}