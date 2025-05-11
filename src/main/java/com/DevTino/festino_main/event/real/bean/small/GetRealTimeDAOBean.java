package com.DevTino.festino_main.event.real.bean.small;

import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import com.DevTino.festino_main.event.real.repository.RealTimeRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetRealTimeDAOBean {

    RealTimeRepositoryJPA realTimeRepositoryJPA;

    @Autowired
    public GetRealTimeDAOBean(RealTimeRepositoryJPA realTimeRepositoryJPA) {
        this.realTimeRepositoryJPA = realTimeRepositoryJPA;
    }

    public RealTimeQuestionDAO exec(){
        return realTimeRepositoryJPA.findByIsOpenTrue();
    }
}
