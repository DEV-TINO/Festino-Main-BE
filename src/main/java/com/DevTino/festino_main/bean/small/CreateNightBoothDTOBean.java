package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.domain.DTO.ResponseNightBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateNightBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseNightBoothDTO exec(NightBoothDAO nightBoothDAO) {
        return ResponseNightBoothDTO.builder()
                .boothId(nightBoothDAO.getBoothId())
                .boothName(nightBoothDAO.getBoothName())
                .adminCategory(nightBoothDAO.getAdminCategory())
                .adminName(nightBoothDAO.getAdminName())
                .openTime(nightBoothDAO.getOpenTime())
                .closeTime(nightBoothDAO.getCloseTime())
                .boothIntro(nightBoothDAO.getBoothIntro())
                .boothImage(nightBoothDAO.getBoothImage())
                .location(nightBoothDAO.getLocation())
                .isOpen(nightBoothDAO.getIsOpen())
                .isOrder(nightBoothDAO.getIsOrder())
                .isReservation(nightBoothDAO.getIsReservation())
                .totalReservationNum(nightBoothDAO.getTotalReservationNum())
                .build();
    }
}