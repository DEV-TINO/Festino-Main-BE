package com.DevTino.festino_main.event.real.bean.small;


import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionGetDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateRealTimeQuestionDTOBean {

    public ResponseRealTimeQuestionGetDTO exec(RealTimeQuestionDAO realTimeQuestionDAO){
        return ResponseRealTimeQuestionGetDTO.builder()
                .question(realTimeQuestionDAO.getQuestion())
                .build();
    }
}
