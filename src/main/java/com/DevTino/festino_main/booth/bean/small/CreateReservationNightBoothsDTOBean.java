package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseReservationNightBoothGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateReservationNightBoothsDTOBean {
    public ResponseReservationNightBoothGetDTO exec(NightBoothDAO nightBoothDAO){
        return ResponseReservationNightBoothGetDTO.builder()
                .boothId(nightBoothDAO.getBoothId())
                .adminName(nightBoothDAO.getAdminName())
                .boothImage(nightBoothDAO.getBoothImage().get(0))
                .totalReservationNum(nightBoothDAO.getTotalReservationNum())
                .isOpen(nightBoothDAO.getIsOpen())
                .build();
    }

    // DAO -> DTO 변환
    public List<ResponseReservationNightBoothGetDTO> exec(List<NightBoothDAO> nightBoothDAOList){

        List<ResponseReservationNightBoothGetDTO> responseReservationNightBoothGetDTOList = new ArrayList<>();

        for(NightBoothDAO nightBoothDAO : nightBoothDAOList){
            ResponseReservationNightBoothGetDTO responseReservationNightBoothGetDTO = exec(nightBoothDAO);

            responseReservationNightBoothGetDTOList.add(responseReservationNightBoothGetDTO);
        }
        return responseReservationNightBoothGetDTOList;
    }
}
