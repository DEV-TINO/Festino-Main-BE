package com.DevTino.festino_main.booth.service;

import com.DevTino.festino_main.booth.bean.GetFacilitiesBean;
import com.DevTino.festino_main.booth.bean.GetFacilityBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFacilityDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilityDTO;
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
    public List<ResponseAllFacilityDTO> getAmenities(){
        return getFacilitiesBean.exec();
    }

    // 편의시설 특정 조회
    public ResponseFacilityDTO getAmenity(UUID boothId){
        return getFacilityBean.exec(boothId);
    }
}
