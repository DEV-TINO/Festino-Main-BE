package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import com.DevTino.festino_main.booth.repository.FacilityRepositoryJPA;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

    // 편의시설 리스트로 가져오기
    public List<FacilityDAO> exec(){

        List<FacilityDAO> daoList = facilityRepositoryJPA.findAllByOrderByIsOpenDescCreateAtAsc();
        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;

    }
}
