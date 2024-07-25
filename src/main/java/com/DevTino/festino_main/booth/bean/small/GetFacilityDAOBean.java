package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import com.DevTino.festino_main.booth.repository.FacilityRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFacilityDAOBean {

    FacilityRepositoryJPA facilityRepositoryJPA;

    @Autowired
    public GetFacilityDAOBean(FacilityRepositoryJPA facilityRepositoryJPA){
        this.facilityRepositoryJPA = facilityRepositoryJPA;
    }

    public FacilityDAO exec(UUID boothId){
        return facilityRepositoryJPA.findById(boothId).orElse(null);
    }
}
