package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetNightBoothBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NightBoothService {

    GetNightBoothBean getNightBoothBean;

    @Autowired
    public NightBoothService(GetNightBoothBean getNightBoothBean){
        this.getNightBoothBean = getNightBoothBean;
    }

    // 야간 부스 디테일 조회
    public ResponseNightBoothDTO getNightBooth(UUID boothId){
        return getNightBoothBean.exec(boothId);
    }
}