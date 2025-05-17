package com.DevTino.festino_main.event.real.bean;

import com.DevTino.festino_main.event.real.bean.small.CreateRealTimeQuestionDTOBean;
import com.DevTino.festino_main.event.real.bean.small.GetRealTimeQuestionDAOBean;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionGetDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GetRealTimeQuestionBean {

    GetRealTimeQuestionDAOBean getRealTimeQuestionDAOBean;
    CreateRealTimeQuestionDTOBean createRealTimeQuestionDTOBean;

    @Autowired
    public GetRealTimeQuestionBean(GetRealTimeQuestionDAOBean getRealTimeQuestionDAOBean, CreateRealTimeQuestionDTOBean createRealTimeQuestionDTOBean) {
        this.getRealTimeQuestionDAOBean = getRealTimeQuestionDAOBean;
        this.createRealTimeQuestionDTOBean = createRealTimeQuestionDTOBean;
    }

    public ResponseRealTimeQuestionGetDTO exec(){
        LocalDateTime now = LocalDateTime.now();

        // 현재 시간이 startTime, endTime 안에 있는 DAO 가져오기
        RealTimeQuestionDAO realTimeQuestionDAO = getRealTimeQuestionDAOBean.exec(now);

        // DTO 생성 후 반환
        return createRealTimeQuestionDTOBean.exec(realTimeQuestionDAO);
    }
}
