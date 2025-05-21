package com.DevTino.festino_main.event.real.bean.small;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeAnswerSaveDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeParticipantDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateRealTimeAnswerDAOBean {
    public RealTimeParticipantDAO exec(RequestRealTimeAnswerSaveDTO requestRealTimeAnswerSaveDTO){
        return RealTimeParticipantDAO.builder()
                .realTimeParticipantId(UUID.randomUUID())
                .mainUserId(requestRealTimeAnswerSaveDTO.getMainUserId())
                .realTimeQuestionId(requestRealTimeAnswerSaveDTO.getRealTimeQuestionId())
                .answer(requestRealTimeAnswerSaveDTO.getAnswer())
                .createAt(DateTimeUtils.nowZone())
                .updateAt(DateTimeUtils.nowZone())
                .build();
    }
}
