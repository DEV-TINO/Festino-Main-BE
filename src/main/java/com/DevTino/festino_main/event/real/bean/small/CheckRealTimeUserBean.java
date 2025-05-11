package com.DevTino.festino_main.event.real.bean.small;

import com.DevTino.festino_main.event.real.repository.RealTimeParticipantRepositoryJPA;
import com.DevTino.festino_main.event.real.repository.RealTimeRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CheckRealTimeUserBean {

    RealTimeParticipantRepositoryJPA realTimeParticipantRepositoryJPA;

    @Autowired
    public CheckRealTimeUserBean(RealTimeParticipantRepositoryJPA realTimeParticipantRepositoryJPA) {
        this.realTimeParticipantRepositoryJPA = realTimeParticipantRepositoryJPA;
    }

    // 해당 mainUserId, RealTimeQuestionId가 존재하면 true 반환
    public Boolean exec(UUID mainUserId, UUID realTimeQuestionId){
        return realTimeParticipantRepositoryJPA.existsByMainUserIdAndRealTimeQuestionId(mainUserId, realTimeQuestionId);
    }
}
