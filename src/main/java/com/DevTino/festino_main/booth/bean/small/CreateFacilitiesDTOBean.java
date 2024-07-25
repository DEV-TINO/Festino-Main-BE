package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilitiesGetDTO;
import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateFacilitiesDTOBean {
    public ResponseFacilitiesGetDTO exec(FacilityDAO facilityDAO){
        return ResponseFacilitiesGetDTO.builder()
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

    // 전체 편의시설 리스트
    public List<ResponseFacilitiesGetDTO> exec(List<FacilityDAO> facilityDAOList){

        List<ResponseFacilitiesGetDTO> responseFacilitiesGetDTOList = new ArrayList<>();

        for (FacilityDAO facilityDAO : facilityDAOList){
            ResponseFacilitiesGetDTO responseFacilitiesGetDTO = exec(facilityDAO);

            responseFacilitiesGetDTOList.add(responseFacilitiesGetDTO);
        }

        return responseFacilitiesGetDTOList;
    }
}
