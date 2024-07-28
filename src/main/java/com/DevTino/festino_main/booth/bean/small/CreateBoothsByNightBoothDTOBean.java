package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateBoothsByNightBoothDTOBean {
    public ResponseBoothsGetDTO exec(NightBoothDAO nightBoothDAO){
        return ResponseBoothsGetDTO.builder()
                .boothId(nightBoothDAO.getBoothId())
                .boothName(nightBoothDAO.getBoothName())
                .adminCategory(nightBoothDAO.getAdminCategory())
                .adminName(nightBoothDAO.getAdminName())
                .openTime(nightBoothDAO.getOpenTime())
                .closeTime(nightBoothDAO.getCloseTime())
                .boothIntro(nightBoothDAO.getBoothIntro())
                .boothImage(nightBoothDAO.getBoothImage().get(0))
                .markerNum(nightBoothDAO.getMarkerNum())
                .location(nightBoothDAO.getLocation())
                .isOpen(nightBoothDAO.getIsOpen())
                .accountInfo(nightBoothDAO.getAccountInfo())
                .build();
    }
}
