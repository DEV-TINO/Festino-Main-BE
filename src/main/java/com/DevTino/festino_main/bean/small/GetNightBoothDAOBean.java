package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.repository.NightBoothRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetNightBoothDAOBean {
    NightBoothRepositoryJPA nightBoothRepositoryJPA;

    @Autowired
    public GetNightBoothDAOBean(NightBoothRepositoryJPA nightBoothRepositoryJPA){
        this.nightBoothRepositoryJPA = nightBoothRepositoryJPA;
    }

    // 야간 부스 가져오기
    public NightBoothDAO exec(UUID boothId){
        return nightBoothRepositoryJPA.findById(boothId).orElse(null);
    }
}