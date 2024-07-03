package com.DevTino.festino_main.bean.small;

import com.DevTino.festino_main.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.repository.NightBoothRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetNightBoothsDAOBean {

    NightBoothRepositoryJPA nightBoothRepositoryJPA;

    @Autowired
    public GetNightBoothsDAOBean(NightBoothRepositoryJPA nightBoothRepositoryJPA){
        this.nightBoothRepositoryJPA = nightBoothRepositoryJPA;
    }

    // 야간 부스 리스트 가져오기
    public List<NightBoothDAO> exec(){
        return nightBoothRepositoryJPA.findAll();
    }
}