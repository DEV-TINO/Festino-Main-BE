package com.DevTino.festino_main.event.real.bean.small;


import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionGetDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateRealTimeQuestionDTOBean {

    // DTO 생성 후 반환
    public ResponseRealTimeQuestionGetDTO exec(RealTimeQuestionDAO realTimeQuestionDAO){
        return ResponseRealTimeQuestionGetDTO.builder()
                .realTimeQuestionId(realTimeQuestionDAO.getRealTimeQuestionId())
                .question(realTimeQuestionDAO.getQuestion())
                .build();
    }
}
