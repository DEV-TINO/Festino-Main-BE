package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetBoothsBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoothService {

    GetBoothsBean getBoothsBean;

    @Autowired
    public BoothService(GetBoothsBean getBoothsBean){
        this.getBoothsBean = getBoothsBean;
    }

    // 부스 전체 조회
    public Map<String, List<ResponseAllBoothDTO>> getBooths(){
        return getBoothsBean.exec();
    }
}