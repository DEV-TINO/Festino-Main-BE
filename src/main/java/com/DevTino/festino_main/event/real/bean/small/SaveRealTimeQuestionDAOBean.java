package com.DevTino.festino_main.event.real.bean.small;

import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import com.DevTino.festino_main.event.real.repository.RealTimeRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveRealTimeQuestionDAOBean {

    RealTimeRepositoryJPA realTimeRepositoryJPA;

    @Autowired
    public SaveRealTimeQuestionDAOBean(RealTimeRepositoryJPA realTimeRepositoryJPA) {
        this.realTimeRepositoryJPA = realTimeRepositoryJPA;
    }

    // DAO 객체 저장
    public void exec(RealTimeQuestionDAO realTimeQuestionDAO){
        realTimeRepositoryJPA.save(realTimeQuestionDAO);
    }
}
