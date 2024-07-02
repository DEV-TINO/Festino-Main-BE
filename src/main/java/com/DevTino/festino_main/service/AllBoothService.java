package com.DevTino.festino_main.service;

import com.DevTino.festino_main.bean.GetAllBoothBean;
import com.DevTino.festino_main.domain.DTO.ResponseAllBoothDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AllBoothService {

    GetAllBoothBean getAllBoothBean;

    @Autowired
    public AllBoothService(GetAllBoothBean getAllBoothBean){
        this.getAllBoothBean = getAllBoothBean;
    }

    // 부스 전체 조회
    public Map<String, List<ResponseAllBoothDTO>> read(){
        return getAllBoothBean.exec();
    }
}