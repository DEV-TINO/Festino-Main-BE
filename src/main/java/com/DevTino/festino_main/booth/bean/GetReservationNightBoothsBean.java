package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateReservationNightBoothsDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetReservationNightBoothsDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseReservationNightBoothGetDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReservationNightBoothsBean {

    GetReservationNightBoothsDAOBean getReservationNightBoothsDAOBean;
    CreateReservationNightBoothsDTOBean createReservationNightBoothsDTOBean;

    @Autowired
    public GetReservationNightBoothsBean(GetReservationNightBoothsDAOBean getReservationNightBoothsDAOBean, CreateReservationNightBoothsDTOBean createReservationNightBoothsDTOBean) {
        this.getReservationNightBoothsDAOBean = getReservationNightBoothsDAOBean;
        this.createReservationNightBoothsDTOBean = createReservationNightBoothsDTOBean;
    }

    // 예약 시 야간 부스 전체 조회
    public List<ResponseReservationNightBoothGetDTO> exec(){

        // 예약가능한 부스 전체 조회
        List<NightBoothDAO> nightBoothDAOList = getReservationNightBoothsDAOBean.exec(true);
        if (nightBoothDAOList.isEmpty())
            return null;

        return createReservationNightBoothsDTOBean.exec(nightBoothDAOList);
    }
}
