package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.DTO.ResponseNightBoothDTO;
import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class SaveNightBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseNightBoothDTO exec(NightBoothDAO nightBoothDAO){
        ResponseNightBoothDTO responseNightBoothDTO = new ResponseNightBoothDTO();

        responseNightBoothDTO.setBoothId(nightBoothDAO.getBoothId());
        responseNightBoothDTO.setBoothName(nightBoothDAO.getBoothName());
        responseNightBoothDTO.setAdminCategory(nightBoothDAO.getAdminCategory());
        responseNightBoothDTO.setAdminName(nightBoothDAO.getAdminName());
        responseNightBoothDTO.setOpenTime(nightBoothDAO.getOpenTime());
        responseNightBoothDTO.setCloseTime(nightBoothDAO.getCloseTime());
        responseNightBoothDTO.setBoothIntro(nightBoothDAO.getBoothIntro());
        responseNightBoothDTO.setBoothImage(nightBoothDAO.getBoothImage());
        responseNightBoothDTO.setLocation(nightBoothDAO.getLocation());
        responseNightBoothDTO.setIsOpen(nightBoothDAO.getIsOpen());
        responseNightBoothDTO.setIsOrder(nightBoothDAO.getIsOrder());
        responseNightBoothDTO.setIsReservation(nightBoothDAO.getIsReservation());
        responseNightBoothDTO.setTotalReservationNum(nightBoothDAO.getTotalReservationNum());

        return responseNightBoothDTO;

    }
}