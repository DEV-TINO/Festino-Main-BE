package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateNightBoothKakaoPayDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothKakaoPayGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetKakaoPayInfoBean {
    GetNightBoothDAOBean getNightBoothDAOBean;
    CreateNightBoothKakaoPayDTOBean createNightBoothKakaoPayDTOBean;

    @Autowired
    public GetKakaoPayInfoBean(GetNightBoothDAOBean getNightBoothDAOBean, CreateNightBoothKakaoPayDTOBean createNightBoothKakaoPayDTOBean) {
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.createNightBoothKakaoPayDTOBean = createNightBoothKakaoPayDTOBean;
    }

    public ResponseNightBoothKakaoPayGetDTO exec(UUID boothId) {
        // boothId를 통해 원하는 객체 찾기
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(boothId);

        // 카카오페이 가능 여부 확인해 예외 처리
        if (!nightBoothDAO.getIsKakaoPay()) throw new ServiceException(ExceptionEnum.KAKAOPAY_DISABLED);

        // 객체를 DTO로 변환해서 반환
        return createNightBoothKakaoPayDTOBean.exec(nightBoothDAO);
    }
}
