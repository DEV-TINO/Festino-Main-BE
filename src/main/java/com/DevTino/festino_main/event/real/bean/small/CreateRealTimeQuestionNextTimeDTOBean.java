package com.DevTino.festino_main.event.real.bean.small;

import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionNextTimeGetDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateRealTimeQuestionNextTimeDTOBean {
    public ResponseRealTimeQuestionNextTimeGetDTO exec(RealTimeQuestionDAO realTimeQuestionDAO){
        return ResponseRealTimeQuestionNextTimeGetDTO.builder()
                .startTime(realTimeQuestionDAO.getStartTime())
                .endTime(realTimeQuestionDAO.getEndTime())
                .build();
    }
}
