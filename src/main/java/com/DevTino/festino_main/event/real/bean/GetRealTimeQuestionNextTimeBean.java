package com.DevTino.festino_main.event.real.bean;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.event.real.bean.small.CreateRealTimeQuestionNextTimeDTOBean;
import com.DevTino.festino_main.event.real.bean.small.GetRealTimeQuestionDAOBean;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionNextTimeGetDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        LocalDateTime now = DateTimeUtils.nowZone();
        LocalDate today = now.toLocalDate();

        LocalDateTime todayStart = today.atTime(0, 0);
        LocalDateTime todayEnd = today.atTime(23, 59, 59);

        // 현재 시간에 맞춰 다음시간인 퀴즈 시작시간, 종료시간 가져오기
        RealTimeQuestionDAO realTimeQuestionDAO = getRealTimeQuestionDAOBean.exec(todayStart, todayEnd, now);

        // 시작시간, 종료시간가 담긴 DTO 객체 반환
        return createRealTimeQuestionNextTimeDTOBean.exec(realTimeQuestionDAO);
    }
}
