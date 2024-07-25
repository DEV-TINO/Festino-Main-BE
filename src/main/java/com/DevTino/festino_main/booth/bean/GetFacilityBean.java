package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateFacilityDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetFacilityDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilityGetDTO;
import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFacilityBean {

    GetFacilityDAOBean getFacilityDAOBean;
    CreateFacilityDTOBean createFacilityDTOBean;

    @Autowired
    public GetFacilityBean(GetFacilityDAOBean getFacilityDAOBean, CreateFacilityDTOBean createFacilityDTOBean){
        this.getFacilityDAOBean = getFacilityDAOBean;
        this.createFacilityDTOBean = createFacilityDTOBean;
    }

    // 특정 편의시설 조회
    public ResponseFacilityGetDTO exec(UUID boothId){
        // 아이디로 특정 편의시설 정보 가져오기
        FacilityDAO facilityDAO = getFacilityDAOBean.exec(boothId);

        // 특정 편의시설 정보 반환
        return createFacilityDTOBean.exec(facilityDAO);
    }
}
