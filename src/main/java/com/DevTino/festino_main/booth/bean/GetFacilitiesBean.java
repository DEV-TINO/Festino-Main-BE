package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateFacilitiesDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetFacilitiesDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFacilityDTO;
import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFacilitiesBean {

    GetFacilitiesDAOBean getFacilitiesDAOBean;
    CreateFacilitiesDTOBean createFacilitiesDTOBean;

    @Autowired
    public GetFacilitiesBean(GetFacilitiesDAOBean getFacilitiesDAOBean, CreateFacilitiesDTOBean createFacilitiesDTOBean){
        this.getFacilitiesDAOBean = getFacilitiesDAOBean;
        this.createFacilitiesDTOBean = createFacilitiesDTOBean;
    }

    public List<ResponseAllFacilityDTO> exec(){
        //
        List<FacilityDAO> facilityDAOList = getFacilitiesDAOBean.exec();

        return createFacilitiesDTOBean.exec(facilityDAOList);
    }
}
