package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.domain.DTO.ResponseDayBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateDayBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseDayBoothDTO exec(DayBoothDAO dayBoothDAO){
        return ResponseDayBoothDTO.builder()
                .boothId(dayBoothDAO.getBoothId())
                .boothName(dayBoothDAO.getBoothName())
                .adminCategory(dayBoothDAO.getAdminCategory())
                .adminName(dayBoothDAO.getAdminName())
                .openTime(dayBoothDAO.getOpenTime())
                .closeTime(dayBoothDAO.getCloseTime())
                .boothIntro(dayBoothDAO.getBoothIntro())
                .boothImage(dayBoothDAO.getBoothImage())
                .location(dayBoothDAO.getLocation())
                .isOpen(dayBoothDAO.getIsOpen())
                .build();
    }
}