package com.DevTino.festino_main.event.real.bean;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.event.real.bean.small.CreateRealTimeQuestionDTOBean;
import com.DevTino.festino_main.event.real.bean.small.GetRealTimeQuestionDAOBean;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionGetDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        LocalDateTime now = DateTimeUtils.nowZone();
        LocalDate today = now.toLocalDate();

        LocalDateTime todayStart = today.atTime(0, 0);
        LocalDateTime todayEnd = today.atTime(23, 59, 59);

        // 현재 시간이 startTime, endTime 안에 있는 DAO 가져오기
        RealTimeQuestionDAO realTimeQuestionDAO = getRealTimeQuestionDAOBean.exec(todayStart, todayEnd, now);

        // DTO 생성 후 반환
        return createRealTimeQuestionDTOBean.exec(realTimeQuestionDAO);
    }
}
