package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateNightBoothTossPayDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothTossPayGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

        // 토스페이 가능 여부 확인해 예외 처리
        if (!nightBoothDAO.getIsTossPay()) throw new ServiceException(ExceptionEnum.TOSSPAY_DISABLED);

        // 객체를 DTO로 변환해서 반환
        return createNightBoothTossPayDTOBean.exec(nightBoothDAO);
    }
}
