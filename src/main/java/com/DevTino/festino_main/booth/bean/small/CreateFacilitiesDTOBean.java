package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFacilityDTO;
import com.DevTino.festino_main.booth.domain.entity.FacilityDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateFacilitiesDTOBean {
    public ResponseAllFacilityDTO exec(FacilityDAO facilityDAO){
        return ResponseAllFacilityDTO.builder()
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
    public List<ResponseAllFacilityDTO> exec(List<FacilityDAO> facilityDAOList){

        List<ResponseAllFacilityDTO> responseAllFacilityDTOList = new ArrayList<>();

        for (FacilityDAO facilityDAO : facilityDAOList){
            ResponseAllFacilityDTO responseAllFacilityDTO = exec(facilityDAO);

            responseAllFacilityDTOList.add(responseAllFacilityDTO);
        }

        return responseAllFacilityDTOList;
    }
}
