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

    // 전체 편의시설 조회
    public List<ResponseAllFacilityDTO> exec(){
        // 편의시설 리스트로 가져오기
        List<FacilityDAO> facilityDAOList = getFacilitiesDAOBean.exec();

        // 전체 편의시설 리스트 반환
        return createFacilitiesDTOBean.exec(facilityDAOList);
    }
}
