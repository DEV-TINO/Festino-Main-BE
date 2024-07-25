package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilityDTO;
import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateFacilityDTOBean {
    public ResponseFacilityDTO exec(FacilityDAO facilityDAO){
        return ResponseFacilityDTO.builder()
                .boothId(facilityDAO.getBoothId())
                .location(facilityDAO.getLocation())
                .markerNum(facilityDAO.getMarkerNum())
                .openTime(facilityDAO.getOpenTime())
                .closeTime(facilityDAO.getCloseTime())
                .build();
    }
}
