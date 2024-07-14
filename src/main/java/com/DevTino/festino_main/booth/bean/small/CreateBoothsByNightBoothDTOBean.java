package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateBoothsByNightBoothDTOBean {
    public ResponseAllBoothDTO exec(NightBoothDAO nightBoothDAO){
        return ResponseAllBoothDTO.builder()
                .boothId(nightBoothDAO.getBoothId())
                .boothName(nightBoothDAO.getBoothName())
                .adminCategory(nightBoothDAO.getAdminCategory())
                .adminName(nightBoothDAO.getAdminName())
                .openTime(nightBoothDAO.getOpenTime())
                .closeTime(nightBoothDAO.getCloseTime())
                .boothIntro(nightBoothDAO.getBoothIntro())
                .boothImage(nightBoothDAO.getBoothImage().get(0))
                .location(nightBoothDAO.getLocation())
                .isOpen(false)
                .build();
    }

    public List<ResponseAllBoothDTO> exec(List<NightBoothDAO> nightBoothDAOList){

        List<ResponseAllBoothDTO> responseDayBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (NightBoothDAO nightBoothDAO : nightBoothDAOList) {

            ResponseAllBoothDTO responseAllBoothDTO = exec(nightBoothDAO);

            responseDayBoothDAOList.add(responseAllBoothDTO);
        }

        return responseDayBoothDAOList;
    }
}
