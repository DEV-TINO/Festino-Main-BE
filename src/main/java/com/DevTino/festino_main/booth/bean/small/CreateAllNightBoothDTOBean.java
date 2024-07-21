package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllNightBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateAllNightBoothDTOBean {
    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseAllNightBoothDTO exec(NightBoothDAO nightBoothDAO){
        return ResponseAllNightBoothDTO.builder()
                .boothId(nightBoothDAO.getBoothId())
                .boothName(nightBoothDAO.getBoothName())
                .adminCategory(nightBoothDAO.getAdminCategory())
                .adminName(nightBoothDAO.getAdminName())
                .openTime(nightBoothDAO.getOpenTime())
                .closeTime(nightBoothDAO.getCloseTime())
                .boothIntro(nightBoothDAO.getBoothIntro())
                .boothImage(nightBoothDAO.getBoothImage().get(0))
                .location(nightBoothDAO.getLocation())
                .isOpen(nightBoothDAO.getIsOpen())
                .build();
    }
}
