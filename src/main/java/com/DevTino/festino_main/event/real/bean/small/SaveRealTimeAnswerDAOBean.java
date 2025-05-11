package com.DevTino.festino_main.event.real.bean.small;

import com.DevTino.festino_main.event.real.domain.RealTimeParticipantDAO;
import com.DevTino.festino_main.event.real.repository.RealTimeParticipantRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveRealTimeAnswerDAOBean {

    RealTimeParticipantRepositoryJPA realTimeParticipantRepositoryJPA;

    @Autowired
    public SaveRealTimeAnswerDAOBean(RealTimeParticipantRepositoryJPA realTimeParticipantRepositoryJPA) {
        this.realTimeParticipantRepositoryJPA = realTimeParticipantRepositoryJPA;
    }

    public void exec(RealTimeParticipantDAO realTimeParticipantDAO){
        realTimeParticipantRepositoryJPA.save(realTimeParticipantDAO);
    }
}
