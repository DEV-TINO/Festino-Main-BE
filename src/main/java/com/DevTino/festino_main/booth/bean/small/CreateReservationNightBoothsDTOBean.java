package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseReservationNightBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateReservationNightBoothsDTOBean {
    public ResponseReservationNightBoothDTO exec(NightBoothDAO nightBoothDAO){
        return ResponseReservationNightBoothDTO.builder()
                .boothId(nightBoothDAO.getBoothId())
                .adminName(nightBoothDAO.getAdminName())
                .boothImage(nightBoothDAO.getBoothImage().get(0))
                .totalReservationNum(nightBoothDAO.getTotalReservationNum())
                .isOpen(nightBoothDAO.getIsOpen())
                .build();
    }

    // DAO -> DTO 변환
    public List<ResponseReservationNightBoothDTO> exec(List<NightBoothDAO> nightBoothDAOList){

        List<ResponseReservationNightBoothDTO> responseReservationNightBoothDTOList = new ArrayList<>();

        for(NightBoothDAO nightBoothDAO : nightBoothDAOList){
            ResponseReservationNightBoothDTO responseReservationNightBoothDTO = exec(nightBoothDAO);

            responseReservationNightBoothDTOList.add(responseReservationNightBoothDTO);
        }
        return responseReservationNightBoothDTOList;
    }
}
