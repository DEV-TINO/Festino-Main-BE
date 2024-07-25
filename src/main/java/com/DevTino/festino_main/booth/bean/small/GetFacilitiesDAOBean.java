package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import com.DevTino.festino_main.booth.repository.FacilityRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFacilitiesDAOBean {

    FacilityRepositoryJPA facilityRepositoryJPA;

    @Autowired
    public GetFacilitiesDAOBean(FacilityRepositoryJPA facilityRepositoryJPA){
        this.facilityRepositoryJPA = facilityRepositoryJPA;
    }

    public List<FacilityDAO> exec(){
        return facilityRepositoryJPA.findAllByOrderByIsOpenDescMarkerNumAsc();
    }
}
