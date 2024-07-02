package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.repository.JPANightBoothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetNightBoothDAOBean {
    JPANightBoothRepository jpaNightBoothRepository;

    @Autowired
    public GetNightBoothDAOBean(JPANightBoothRepository jpaNightBoothRepository){
        this.jpaNightBoothRepository = jpaNightBoothRepository;
    }

    // 야간 부스 가져오기
    public NightBoothDAO exec(UUID boothId){
        return jpaNightBoothRepository.findById(boothId).get();
    }
}