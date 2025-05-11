package com.DevTino.festino_main.event.real.service;

import com.DevTino.festino_main.event.real.bean.*;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeAnswerSaveDTO;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeQuestionSaveDTO;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionGetDTO;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionNextTimeGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RealTimeService {

    GetRealTimeQuestionBean getRealTimeQuestionBean;
    SaveRealTimeQuestionBean saveRealTimeQuestionBean;
    SaveRealTimeAnswerBean saveRealTimeAnswerBean;
    GetRealTimeAlreadyParticipantBean getRealTimeAlreadyParticipantBean;
    GetRealTimeQuestionNextTimeBean getRealTimeQuestionNextTimeBean;

    @Autowired
    public RealTimeService(GetRealTimeQuestionBean getRealTimeQuestionBean, SaveRealTimeQuestionBean saveRealTimeQuestionBean, SaveRealTimeAnswerBean saveRealTimeAnswerBean, GetRealTimeAlreadyParticipantBean getRealTimeAlreadyParticipantBean, GetRealTimeQuestionNextTimeBean getRealTimeQuestionNextTimeBean) {
        this.getRealTimeQuestionBean = getRealTimeQuestionBean;
        this.saveRealTimeQuestionBean = saveRealTimeQuestionBean;
        this.saveRealTimeAnswerBean = saveRealTimeAnswerBean;
        this.getRealTimeAlreadyParticipantBean = getRealTimeAlreadyParticipantBean;
        this.getRealTimeQuestionNextTimeBean = getRealTimeQuestionNextTimeBean;
    }

    // 문제 조회
    public ResponseRealTimeQuestionGetDTO getRealTimeQuestion(){
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

    // 문제 시간 조회
    public ResponseRealTimeQuestionNextTimeGetDTO getRealTimeQuestionNextTime(){
        return getRealTimeQuestionNextTimeBean.exec();
    }
}
