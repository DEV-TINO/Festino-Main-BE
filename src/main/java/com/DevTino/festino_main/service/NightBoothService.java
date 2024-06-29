package com.DevTino.festino_main.service;

import com.DevTino.festino_main.bean.GetNightBoothBean;
import com.DevTino.festino_main.domain.DTO.ResponseNightBoothDTO;
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

    public ResponseNightBoothDTO read(UUID boothId){
        return getNightBoothBean.exec(boothId);
    }
}