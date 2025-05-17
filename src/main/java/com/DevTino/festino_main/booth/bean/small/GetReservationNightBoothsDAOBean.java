package com.DevTino.festino_main.booth.bean.small;

import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.booth.repository.NightBoothRepositoryJPA;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReservationNightBoothsDAOBean {

    NightBoothRepositoryJPA nightBoothRepositoryJPA;

    @Autowired
    public GetReservationNightBoothsDAOBean(NightBoothRepositoryJPA nightBoothRepositoryJPA){
        this.nightBoothRepositoryJPA = nightBoothRepositoryJPA;
    }

    // isReservation이 true 인 것만 가져와서 정렬
    public List<NightBoothDAO> exec(Boolean isReservation){

        List<NightBoothDAO> daoList = nightBoothRepositoryJPA.findByIsReservation(isReservation);
        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;

    }

}