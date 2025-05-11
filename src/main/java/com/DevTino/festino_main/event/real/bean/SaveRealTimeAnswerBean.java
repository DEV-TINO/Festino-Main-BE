package com.DevTino.festino_main.event.real.bean;

import com.DevTino.festino_main.event.real.bean.small.CheckRealTimeUserBean;
import com.DevTino.festino_main.event.real.bean.small.CreateRealTimeAnswerDAOBean;
import com.DevTino.festino_main.event.real.bean.small.SaveRealTimeAnswerDAOBean;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeAnswerSaveDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeParticipantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveRealTimeAnswerBean {

    CreateRealTimeAnswerDAOBean createRealTimeAnswerDAOBean;
    CheckRealTimeUserBean checkRealTimeUserBean;
    SaveRealTimeAnswerDAOBean saveRealTimeAnswerDAOBean;

    @Autowired
    public SaveRealTimeAnswerBean(CreateRealTimeAnswerDAOBean createRealTimeAnswerDAOBean, CheckRealTimeUserBean checkRealTimeUserBean, SaveRealTimeAnswerDAOBean saveRealTimeAnswerDAOBean) {
        this.createRealTimeAnswerDAOBean = createRealTimeAnswerDAOBean;
        this.checkRealTimeUserBean = checkRealTimeUserBean;
        this.saveRealTimeAnswerDAOBean = saveRealTimeAnswerDAOBean;
    }

    public UUID exec(RequestRealTimeAnswerSaveDTO requestRealTimeAnswerSaveDTO){

        // DAO 객체 가져오기
        RealTimeParticipantDAO realTimeParticipantDAO = createRealTimeAnswerDAOBean.exec(requestRealTimeAnswerSaveDTO);
        if(realTimeParticipantDAO == null) return null;

        // DAO 객체 저장
        saveRealTimeAnswerDAOBean.exec(realTimeParticipantDAO);

        // PK 값 반환
        return realTimeParticipantDAO.getRealTimeParticipantId();
    }
}
