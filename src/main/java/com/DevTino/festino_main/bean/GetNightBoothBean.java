package com.DevTino.festino_main.bean;

import com.DevTino.festino_main.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.bean.small.SaveNightBoothDTOBean;
import com.DevTino.festino_main.domain.DTO.ResponseNightBoothDTO;
import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetNightBoothBean {
    GetNightBoothDAOBean getNightBoothDAOBean;
    SaveNightBoothDTOBean saveNightBoothDTOBean;

    @Autowired
    public GetNightBoothBean(GetNightBoothDAOBean getNightBoothDAOBean, SaveNightBoothDTOBean saveNightBoothDTOBean){
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.saveNightBoothDTOBean = saveNightBoothDTOBean;
    }

    // 야간 부스 dao 가져온 후 dto 반환
    public ResponseNightBoothDTO exec(UUID boothId){

        // 야간 부스 디테일 dao 가져오기
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(boothId);

        // 가져온 dao를 dto로 변환
        return saveNightBoothDTOBean.exec(nightBoothDAO);
    }
}
