package com.DevTino.festino_main.event.real.bean;

import com.DevTino.festino_main.event.real.bean.small.CreateRealTimeQuestionNextTimeDTOBean;
import com.DevTino.festino_main.event.real.bean.small.GetRealTimeQuestionDAOBean;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionNextTimeGetDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GetRealTimeQuestionNextTimeBean {

    GetRealTimeQuestionDAOBean getRealTimeQuestionDAOBean;
    CreateRealTimeQuestionNextTimeDTOBean createRealTimeQuestionNextTimeDTOBean;

    @Autowired
    public GetRealTimeQuestionNextTimeBean(GetRealTimeQuestionDAOBean getRealTimeQuestionDAOBean, CreateRealTimeQuestionNextTimeDTOBean createRealTimeQuestionNextTimeDTOBean) {
        this.getRealTimeQuestionDAOBean = getRealTimeQuestionDAOBean;
        this.createRealTimeQuestionNextTimeDTOBean = createRealTimeQuestionNextTimeDTOBean;
    }

    public ResponseRealTimeQuestionNextTimeGetDTO exec(){
        // 현재 시간 가져오기
        LocalDateTime now = LocalDateTime.now();

        // 현재 시간에 맞춰 다음시간인 퀴즈 시작시간, 종료시간 가져오기
        RealTimeQuestionDAO realTimeQuestionDAO = getRealTimeQuestionDAOBean.exec(now);
        if (realTimeQuestionDAO == null) return null;

        // 시작시간, 종료시간가 담긴 DTO 객체 반환
        return createRealTimeQuestionNextTimeDTOBean.exec(realTimeQuestionDAO);
    }
}
