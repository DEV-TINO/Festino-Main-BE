package com.DevTino.festino_main.event.real.bean;

import com.DevTino.festino_main.event.real.bean.small.CreateRealTimeQuestionDAOBean;
import com.DevTino.festino_main.event.real.bean.small.SaveRealTimeQuestionDAOBean;
import com.DevTino.festino_main.event.real.domain.DTO.RequestRealTimeQuestionSaveDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveRealTimeQuestionBean {

    CreateRealTimeQuestionDAOBean createRealTimeQuestionDAOBean;
    SaveRealTimeQuestionDAOBean saveRealTimeQuestionDAOBean;

    @Autowired
    public SaveRealTimeQuestionBean(CreateRealTimeQuestionDAOBean createRealTimeQuestionDAOBean, SaveRealTimeQuestionDAOBean saveRealTimeQuestionDAOBean) {
        this.createRealTimeQuestionDAOBean = createRealTimeQuestionDAOBean;
        this.saveRealTimeQuestionDAOBean = saveRealTimeQuestionDAOBean;
    }

    public UUID exec(RequestRealTimeQuestionSaveDTO requestRealTimeQuestionSaveDTO){

        // 문제 DAO객체 생성
        RealTimeQuestionDAO realTimeQuestionDAO = createRealTimeQuestionDAOBean.exec(requestRealTimeQuestionSaveDTO);

        // DAO 객체 저장
        saveRealTimeQuestionDAOBean.exec(realTimeQuestionDAO);

        // PK값 반환
        return realTimeQuestionDAO.getRealTimeQuestionId();
    }
}
