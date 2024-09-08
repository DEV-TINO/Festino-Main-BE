package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilityGetDTO;
import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateFacilityDTOBean {
    public ResponseFacilityGetDTO exec(FacilityDAO facilityDAO){
        return ResponseFacilityGetDTO.builder()
                .boothId(facilityDAO.getBoothId())
                .location(facilityDAO.getLocation())
                .markerNum(facilityDAO.getMarkerNum())
                .openTime(facilityDAO.getOpenTime())
                .closeTime(facilityDAO.getCloseTime())
                .boothName(facilityDAO.getBoothName())
                .adminCategory(facilityDAO.getAdminCategory())
                .instagram(facilityDAO.getInstagram())
                .build();
    }
}
