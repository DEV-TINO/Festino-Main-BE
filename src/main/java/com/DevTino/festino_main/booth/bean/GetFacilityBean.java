package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateFacilityDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetFacilityDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilityDTO;
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

    public ResponseFacilityDTO exec(UUID boothId){
        FacilityDAO facilityDAO = getFacilityDAOBean.exec(boothId);

        return createFacilityDTOBean.exec(facilityDAO);
    }
}
