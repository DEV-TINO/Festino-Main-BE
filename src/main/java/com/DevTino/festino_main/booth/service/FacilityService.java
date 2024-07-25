package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetFacilitiesBean;
import com.DevTino.festino_main.booth.bean.GetFacilityBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilitiesGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilityGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FacilityService {

    GetFacilitiesBean getFacilitiesBean;
    GetFacilityBean getFacilityBean;

    @Autowired
    public FacilityService(GetFacilitiesBean getFacilitiesBean, GetFacilityBean getFacilityBean){
        this.getFacilitiesBean = getFacilitiesBean;
        this.getFacilityBean = getFacilityBean;
    }

    // 전체 편의시설 조회
    public List<ResponseFacilitiesGetDTO> getFacilities(){
        return getFacilitiesBean.exec();
    }

    // 편의시설 특정 조회
    public ResponseFacilityGetDTO getFacility(UUID boothId){
        return getFacilityBean.exec(boothId);
    }
}
