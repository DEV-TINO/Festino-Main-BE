package com.DevTino.festino_main.event.real.bean;

import com.DevTino.festino_main.event.real.bean.small.CheckRealTimeUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetRealTimeAlreadyParticipantBean {

    CheckRealTimeUserBean checkRealTimeUserBean;

    @Autowired
    public GetRealTimeAlreadyParticipantBean(CheckRealTimeUserBean checkRealTimeUserBean) {
        this.checkRealTimeUserBean = checkRealTimeUserBean;
    }

    // 참여 여부 가져오기
    public boolean exec(UUID mainUserId, UUID realTimeQuestionId){
        return checkRealTimeUserBean.exec(mainUserId, realTimeQuestionId);
    }
}
