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

    public ResponseNightBoothDTO exec(UUID boothId){
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(boothId);

        return saveNightBoothDTOBean.exec(nightBoothDAO);
    }
}
