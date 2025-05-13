package com.DevTino.festino_main.event.real.bean.small;

import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import com.DevTino.festino_main.event.real.repository.RealTimeRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GetRealTimeQuestionDAOBean {

    RealTimeRepositoryJPA realTimeRepositoryJPA;

    @Autowired
    public GetRealTimeQuestionDAOBean(RealTimeRepositoryJPA realTimeRepositoryJPA) {
        this.realTimeRepositoryJPA = realTimeRepositoryJPA;
    }

    // 현재 시간이 startTime, endTime 안에 있는 DAO 가져오기
    public RealTimeQuestionDAO exec(LocalDateTime now){
        return realTimeRepositoryJPA.findTop1ByStartTimeLessThanEqualAndEndTimeGreaterThanEqualOrderByStartTimeAsc(now, now);
    }

//    public RealTimeQuestionDAO exec(LocalDateTime now){
//        return realTimeRepositoryJPA.findFirstByStartTimeAfterOrderByStartTimeAsc(now);
//    }
}
