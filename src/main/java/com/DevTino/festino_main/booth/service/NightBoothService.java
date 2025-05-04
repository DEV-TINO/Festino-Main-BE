package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.*;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothTossPayGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseReservationNightBoothGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class NightBoothService {

    GetNightBoothBean getNightBoothBean;
    GetReservationNightBoothsBean getReservationNightBoothsBean;
    GetNightBoothsBean getNightBoothsBean;
    GetAccountInfoBean getAccountInfoBean;
    GetTossPayInfoBean getTossPayInfoBean;

    @Autowired
    public NightBoothService(GetNightBoothBean getNightBoothBean, GetReservationNightBoothsBean getReservationNightBoothsBean, GetNightBoothsBean getNightBoothsBean, GetAccountInfoBean getAccountInfoBean, GetTossPayInfoBean getTossPayInfoBean) {
        this.getNightBoothBean = getNightBoothBean;
        this.getReservationNightBoothsBean = getReservationNightBoothsBean;
        this.getNightBoothsBean = getNightBoothsBean;
        this.getAccountInfoBean = getAccountInfoBean;
        this.getTossPayInfoBean = getTossPayInfoBean;
    }

    // 야간 부스 디테일 조회
    public ResponseNightBoothGetDTO getNightBooth(UUID boothId){
        return getNightBoothBean.exec(boothId);
    }

    // 예약 시 야간 부스 전체 조회
    public List<ResponseReservationNightBoothGetDTO> getReservationNightBooths(){
        return getReservationNightBoothsBean.exec();
    }

    // 야간 부스 전체 조회
    public List<ResponseNightBoothsGetDTO> getNightBooths(){
        return getNightBoothsBean.exec();
    }

    // 계좌 정보 조회
    public Map<String, String> getAccountInfo(UUID boothId) { return getAccountInfoBean.exec(boothId); }

    // 토스페이 조회
    public ResponseNightBoothTossPayGetDTO getTossPayInfo(UUID boothId) {
        return getTossPayInfoBean.exec(boothId);
    }
}