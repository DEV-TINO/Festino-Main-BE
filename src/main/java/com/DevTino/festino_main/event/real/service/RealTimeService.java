package com.DevTino.festino_main.event.real.service;

import com.DevTino.festino_main.event.real.bean.GetRealTimeAlreadyParticipantBean;
import com.DevTino.festino_main.event.real.bean.GetRealTimeQuestionBean;
import com.DevTino.festino_main.event.real.bean.SaveRealTimeAnswerBean;
import com.DevTino.festino_main.event.real.bean.SaveRealTimeQuestionBean;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeAnswerSaveDTO;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeQuestionSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RealTimeService {

    GetRealTimeQuestionBean getRealTimeQuestionBean;
    SaveRealTimeQuestionBean saveRealTimeQuestionBean;
    SaveRealTimeAnswerBean saveRealTimeAnswerBean;
    GetRealTimeAlreadyParticipantBean getRealTimeAlreadyParticipantBean;

    @Autowired
    public RealTimeService(GetRealTimeQuestionBean getRealTimeQuestionBean, SaveRealTimeQuestionBean saveRealTimeQuestionBean, SaveRealTimeAnswerBean saveRealTimeAnswerBean, GetRealTimeAlreadyParticipantBean getRealTimeAlreadyParticipantBean) {
        this.getRealTimeQuestionBean = getRealTimeQuestionBean;
        this.saveRealTimeQuestionBean = saveRealTimeQuestionBean;
        this.saveRealTimeAnswerBean = saveRealTimeAnswerBean;
        this.getRealTimeAlreadyParticipantBean = getRealTimeAlreadyParticipantBean;
    }

    // 문제 조회
    public String getRealTimeQuestion(){
        return getRealTimeQuestionBean.exec();
    }

    // 문제 생성
    public UUID saveRealTimeQuestion(RequestRealTimeQuestionSaveDTO requestRealTimeQuestionSaveDTO){
        return saveRealTimeQuestionBean.exec(requestRealTimeQuestionSaveDTO);
    }

    // 답변 저장
    public UUID saveRealTimeAnswer(RequestRealTimeAnswerSaveDTO requestRealTimeAnswerSaveDTO){
        return saveRealTimeAnswerBean.exec(requestRealTimeAnswerSaveDTO);
    }

    // 참여 여부 조회
    public boolean getRealTimeAlreadyParticipated(UUID mainUserId, UUID realTimeQuestionId){
        return getRealTimeAlreadyParticipantBean.exec(mainUserId, realTimeQuestionId);
    }
}
