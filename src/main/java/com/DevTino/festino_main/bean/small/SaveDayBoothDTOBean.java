package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.DTO.ResponseDayBoothDTO;
import com.DevTino.festino_main.domain.entity.DayBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class SaveDayBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseDayBoothDTO exec(DayBoothDAO dayBoothDAO){
        ResponseDayBoothDTO responseDayBoothDTO = new ResponseDayBoothDTO();

        responseDayBoothDTO.setBoothId(dayBoothDAO.getBoothId());
        responseDayBoothDTO.setBoothName(dayBoothDAO.getBoothName());
        responseDayBoothDTO.setAdminCategory(dayBoothDAO.getAdminCategory());
        responseDayBoothDTO.setAdminName(dayBoothDAO.getAdminName());
        responseDayBoothDTO.setOpenTime(dayBoothDAO.getOpenTime());
        responseDayBoothDTO.setCloseTime(dayBoothDAO.getCloseTime());
        responseDayBoothDTO.setBoothIntro(dayBoothDAO.getBoothIntro());
        responseDayBoothDTO.setBoothImage(dayBoothDAO.getBoothImage());
        responseDayBoothDTO.setLocation(dayBoothDAO.getLocation());
        responseDayBoothDTO.setIsOpen(dayBoothDAO.getIsOpen());

        return responseDayBoothDTO;

    }
}