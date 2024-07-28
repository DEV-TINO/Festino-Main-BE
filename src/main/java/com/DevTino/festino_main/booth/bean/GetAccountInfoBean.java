package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.AccountInfo;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetAccountInfoBean {
    GetNightBoothDAOBean getNightBoothDAOBean;

    @Autowired
    public GetAccountInfoBean(GetNightBoothDAOBean getNightBoothDAOBean) {
        this.getNightBoothDAOBean = getNightBoothDAOBean;
    }

    public AccountInfo exec(UUID boothId) {
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(boothId);

        if(nightBoothDAO == null) return null;
        return nightBoothDAO.getAccountInfo();
    }
}
