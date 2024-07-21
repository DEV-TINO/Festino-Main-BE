package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllDayBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateAllDayBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseAllDayBoothDTO exec(DayBoothDAO dayBoothDAO){
        return ResponseAllDayBoothDTO.builder()
                .boothId(dayBoothDAO.getBoothId())
                .boothNum(dayBoothDAO.getBoothNum())
                .boothName(dayBoothDAO.getBoothName())
                .adminCategory(dayBoothDAO.getAdminCategory())
                .adminName(dayBoothDAO.getAdminName())
                .openTime(dayBoothDAO.getOpenTime())
                .closeTime(dayBoothDAO.getCloseTime())
                .boothIntro(dayBoothDAO.getBoothIntro())
                .boothImage(dayBoothDAO.getBoothImage().get(0))
                .location(dayBoothDAO.getLocation())
                .isOpen(dayBoothDAO.getIsOpen())
                .build();
    }
}
