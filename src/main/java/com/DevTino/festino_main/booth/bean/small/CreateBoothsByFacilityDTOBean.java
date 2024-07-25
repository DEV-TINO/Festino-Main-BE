package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateBoothsByFacilityDTOBean {
    public ResponseAllBoothDTO exec(FacilityDAO facilityDAO) {
        return ResponseAllBoothDTO.builder()
                .boothId(facilityDAO.getBoothId())
                .boothName(facilityDAO.getBoothName())
                .adminCategory(facilityDAO.getAdminCategory())
                .adminName(facilityDAO.getAdminName())
                .openTime(facilityDAO.getOpenTime())
                .closeTime(facilityDAO.getCloseTime())
                .boothIntro(facilityDAO.getBoothIntro())
                .boothImage(facilityDAO.getBoothImage())
                .markerNum(facilityDAO.getMarkerNum())
                .location(facilityDAO.getLocation())
                .isOpen(facilityDAO.getIsOpen())
                .build();
    }
}
