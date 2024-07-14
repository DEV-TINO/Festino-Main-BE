package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetBoothsBean;
import com.DevTino.festino_main.booth.bean.GetDayBoothBean;
import com.DevTino.festino_main.booth.bean.GetFoodBoothBean;
import com.DevTino.festino_main.booth.bean.GetNightBoothBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFoodBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BoothService {

    GetBoothsBean getBoothsBean;
    GetDayBoothBean getDayBoothBean;
    GetFoodBoothBean getFoodBoothBean;
    GetNightBoothBean getNightBoothBean;

    @Autowired
    public BoothService(GetBoothsBean getBoothsBean, GetDayBoothBean getDayBoothBean, GetFoodBoothBean getFoodBoothBean, GetNightBoothBean getNightBoothBean){
        this.getBoothsBean = getBoothsBean;
        this.getDayBoothBean = getDayBoothBean;
        this.getFoodBoothBean = getFoodBoothBean;
        this.getNightBoothBean = getNightBoothBean;
    }

    // 부스 전체 조회
    public Map<String, List<ResponseAllBoothDTO>> getBooths(){
        return getBoothsBean.exec();
    }

    // 주간 부스 디테일 조회
    public ResponseDayBoothDTO getDayBooth(UUID boothId) {
        return getDayBoothBean.exec(boothId);
    }

    // 푸드트럭 디테일 조회
    public ResponseFoodBoothDTO getFoodBooth(UUID boothId){
        return getFoodBoothBean.exec(boothId);
    }

    // 야간 부스 디테일 조회
    public ResponseNightBoothDTO getNightBooth(UUID boothId){
        return getNightBoothBean.exec(boothId);
    }
}