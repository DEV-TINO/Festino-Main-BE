package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.booth.repository.NightBoothRepositoryJPA;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class GetNightBoothDAOBean {
    NightBoothRepositoryJPA nightBoothRepositoryJPA;

    @Autowired
    public GetNightBoothDAOBean(NightBoothRepositoryJPA nightBoothRepositoryJPA){
        this.nightBoothRepositoryJPA = nightBoothRepositoryJPA;
    }

    // 야간 부스 가져오기
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public NightBoothDAO exec(UUID boothId){
        return nightBoothRepositoryJPA.findById(boothId).orElse(null);
    }
}