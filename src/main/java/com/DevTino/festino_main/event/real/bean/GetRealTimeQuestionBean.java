package com.DevTino.festino_main.event.real.bean;

import com.DevTino.festino_main.event.real.bean.small.CreateRealTimeQuestionDTOBean;
import com.DevTino.festino_main.event.real.bean.small.GetRealTimeDAOBean;
import com.DevTino.festino_main.event.real.domain.DTO.ResponseRealTimeQuestionGetDTO;
import com.DevTino.festino_main.event.real.domain.RealTimeQuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetRealTimeQuestionBean {

    GetRealTimeDAOBean getRealTimeDAOBean;
    CreateRealTimeQuestionDTOBean createRealTimeQuestionDTOBean;

    @Autowired
    public GetRealTimeQuestionBean(GetRealTimeDAOBean getRealTimeDAOBean, CreateRealTimeQuestionDTOBean createRealTimeQuestionDTOBean) {
        this.getRealTimeDAOBean = getRealTimeDAOBean;
        this.createRealTimeQuestionDTOBean = createRealTimeQuestionDTOBean;
    }

    public ResponseRealTimeQuestionGetDTO exec(){
        // isOpen이 true인 DAO 객체 가져오기
        RealTimeQuestionDAO realTimeQuestionDAO = getRealTimeDAOBean.exec();
        if (realTimeQuestionDAO == null) return null;

        // DTO 생성 후 반환
        return createRealTimeQuestionDTOBean.exec(realTimeQuestionDAO);
    }
}
