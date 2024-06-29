package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.DTO.ResponseNightBoothDTO;
import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class SaveNightBoothDTOBean {

    // 가져온 dao를 바탕으로 dto로 변경
    public ResponseNightBoothDTO exec(NightBoothDAO nightBoothDAO){
        ResponseNightBoothDTO responseNightBoothDTO = new ResponseNightBoothDTO();

        responseNightBoothDTO.setBoothName(nightBoothDAO.getBoothName());
        responseNightBoothDTO.setAdminCategory(nightBoothDAO.getAdminCategory());
        responseNightBoothDTO.setOpenTime(nightBoothDAO.getOpenTime());
        responseNightBoothDTO.setCloseTime(nightBoothDAO.getCloseTime());
        responseNightBoothDTO.setBoothIntro(nightBoothDAO.getBoothIntro());
        responseNightBoothDTO.setBoothImage(nightBoothDAO.getBoothImage());
        responseNightBoothDTO.setLocation(nightBoothDAO.getLocation());
        responseNightBoothDTO.setDescripteImage(nightBoothDAO.getDescripteImage());
        responseNightBoothDTO.setDescripte(nightBoothDAO.getDescription());
        responseNightBoothDTO.setIsOpen(nightBoothDAO.getIsOpen());
        responseNightBoothDTO.setTotalTeam(nightBoothDAO.getTotalTeam());

        return responseNightBoothDTO;

    }
}