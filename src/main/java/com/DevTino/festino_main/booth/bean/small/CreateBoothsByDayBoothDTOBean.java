package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.DayBoothDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateBoothsByDayBoothDTOBean {
    public ResponseAllBoothDTO exec(DayBoothDAO dayBoothDAO){
        return ResponseAllBoothDTO.builder()
                .boothId(dayBoothDAO.getBoothId())
                .boothName(dayBoothDAO.getBoothName())
                .adminCategory(dayBoothDAO.getAdminCategory())
                .adminName(dayBoothDAO.getAdminName())
                .openTime(dayBoothDAO.getOpenTime())
                .closeTime(dayBoothDAO.getCloseTime())
                .boothIntro(dayBoothDAO.getBoothIntro())
                .boothImage(dayBoothDAO.getBoothImage().get(0))
                .location(dayBoothDAO.getLocation())
                .isOpen(false)
                .build();
    }

    public List<ResponseAllBoothDTO> exec(List<DayBoothDAO> dayBoothDAOList){

        List<ResponseAllBoothDTO> responseDayBoothDAOList = new ArrayList<>();

        // 주간 부스 전체 리스트로 가져오기
        for (DayBoothDAO dayBoothDAO : dayBoothDAOList) {

            ResponseAllBoothDTO responseAllBoothDTO = exec(dayBoothDAO);

            responseDayBoothDAOList.add(responseAllBoothDTO);
        }

        return responseDayBoothDAOList;
    }
}
