package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetAccountInfoBean;
import com.DevTino.festino_main.booth.bean.GetNightBoothBean;
import com.DevTino.festino_main.booth.bean.GetNightBoothsBean;
import com.DevTino.festino_main.booth.bean.GetReservationNightBoothsBean;
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

    @Autowired
    public NightBoothService(GetNightBoothBean getNightBoothBean, GetReservationNightBoothsBean getReservationNightBoothsBean, GetNightBoothsBean getNightBoothsBean, GetAccountInfoBean getAccountInfoBean){
        this.getNightBoothBean = getNightBoothBean;
        this.getReservationNightBoothsBean = getReservationNightBoothsBean;
        this.getNightBoothsBean = getNightBoothsBean;
        this.getAccountInfoBean = getAccountInfoBean;
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
}