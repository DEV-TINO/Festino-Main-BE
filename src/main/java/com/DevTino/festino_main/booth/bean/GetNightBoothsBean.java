package com.DevTino.festino_main.booth.bean;

import com.DevTino.festino_main.booth.bean.small.CreateNightBoothsDTOBean;
import com.DevTino.festino_main.booth.bean.small.GetNightBoothsDAOBean;
import com.DevTino.festino_main.booth.domain.DTO.ResponseReservationNightBoothDTO;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetNightBoothsBean {

    GetNightBoothsDAOBean getNightBoothsDAOBean;
    CreateNightBoothsDTOBean createNightBoothsDTOBean;

    @Autowired
    public GetNightBoothsBean(GetNightBoothsDAOBean getNightBoothsDAOBean, CreateNightBoothsDTOBean createNightBoothsDTOBean) {
        this.getNightBoothsDAOBean = getNightBoothsDAOBean;
        this.createNightBoothsDTOBean = createNightBoothsDTOBean;
    }

    // 예약 시 야간 부스 전체 조회
    public List<ResponseReservationNightBoothDTO> exec(){

        // 예약가능한 부스 전체 조회
        List<NightBoothDAO> nightBoothDAOList = getNightBoothsDAOBean.exec(true);
        if (nightBoothDAOList.isEmpty())
            return null;

        return createNightBoothsDTOBean.exec(nightBoothDAOList);
    }
}
