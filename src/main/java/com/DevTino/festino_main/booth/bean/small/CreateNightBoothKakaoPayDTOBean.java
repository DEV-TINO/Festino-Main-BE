package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothKakaoPayGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateNightBoothKakaoPayDTOBean {
    // 찾은 객체 DTO로 변환
    public ResponseNightBoothKakaoPayGetDTO exec(NightBoothDAO nightBoothDAO) {
        return ResponseNightBoothKakaoPayGetDTO.builder()
                .boothId(nightBoothDAO.getBoothId())
                .isKakaoPay(nightBoothDAO.getIsKakaoPay())
                .kakaoPay(nightBoothDAO.getKakaoPay())
                .build();
    }
}
