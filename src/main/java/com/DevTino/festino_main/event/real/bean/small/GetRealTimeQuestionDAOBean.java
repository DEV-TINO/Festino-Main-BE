package com.DevTino.festino_main.event.real.bean.small;

import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import com.DevTino.festino_main.event.real.repository.RealTimeRepositoryJPA;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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
    public RealTimeQuestionDAO exec(LocalDateTime todayStart, LocalDateTime todayEnd, LocalDateTime now){
      
        RealTimeQuestionDAO dao = realTimeRepositoryJPA.findTop1ByStartTimeBetweenAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualOrderByStartTimeAsc(todayStart, todayEnd, now, now).orElse(null);
        if (dao == null) dao = realTimeRepositoryJPA.findTop1ByStartTimeAfterOrderByStartTimeAsc(now).orElse(null);
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);
      
        return dao;
  
    }

//    public RealTimeQuestionDAO exec(LocalDateTime now){
//        return realTimeRepositoryJPA.findFirstByStartTimeAfterOrderByStartTimeAsc(now);
//    }
}
