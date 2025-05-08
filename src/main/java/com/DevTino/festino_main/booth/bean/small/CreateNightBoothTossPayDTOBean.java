package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothTossPayGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateNightBoothTossPayDTOBean {
    // 찾은 객체 DTO로 변환
    public ResponseNightBoothTossPayGetDTO exec(NightBoothDAO nightBoothDAO) {
        return ResponseNightBoothTossPayGetDTO.builder()
                .boothId(nightBoothDAO.getBoothId())
                .isTossPay(nightBoothDAO.getIsTossPay())
                .tossPay(nightBoothDAO.getTossPay())
                .build();
    }

}
