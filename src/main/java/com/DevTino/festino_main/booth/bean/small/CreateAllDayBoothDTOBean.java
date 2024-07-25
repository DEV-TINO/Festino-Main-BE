package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateAllDayBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseDayBoothsGetDTO exec(DayBoothDAO dayBoothDAO){
        return ResponseDayBoothsGetDTO.builder()
                .boothId(dayBoothDAO.getBoothId())
                .boothName(dayBoothDAO.getBoothName())
                .adminCategory(dayBoothDAO.getAdminCategory())
                .adminName(dayBoothDAO.getAdminName())
                .openTime(dayBoothDAO.getOpenTime())
                .closeTime(dayBoothDAO.getCloseTime())
                .boothIntro(dayBoothDAO.getBoothIntro())
                .boothImage(dayBoothDAO.getBoothImage().get(0))
                .markerNum(dayBoothDAO.getMarkerNum())
                .location(dayBoothDAO.getLocation())
                .isOpen(dayBoothDAO.getIsOpen())
                .build();
    }
}
