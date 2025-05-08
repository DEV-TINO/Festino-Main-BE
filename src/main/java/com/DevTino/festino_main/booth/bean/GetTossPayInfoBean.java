package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateNightBoothTossPayDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothTossPayGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetTossPayInfoBean {
    GetNightBoothDAOBean getNightBoothDAOBean;
    CreateNightBoothTossPayDTOBean createNightBoothTossPayDTOBean;

    @Autowired
    public GetTossPayInfoBean(GetNightBoothDAOBean getNightBoothDAOBean, CreateNightBoothTossPayDTOBean createNightBoothTossPayDTOBean) {
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.createNightBoothTossPayDTOBean = createNightBoothTossPayDTOBean;
    }

    // 토스페이 조회
    public ResponseNightBoothTossPayGetDTO exec(UUID boothId) {
        // boothId를 통해 원하는 객체 찾기
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(boothId);
        if (nightBoothDAO == null) return null;

        // isTossPay 여부를 통해 예외처리
        if (!nightBoothDAO.getIsTossPay()) return null;

        // 객체를 DTO로 변환해서 반환
        return createNightBoothTossPayDTOBean.exec(nightBoothDAO);
    }
}
