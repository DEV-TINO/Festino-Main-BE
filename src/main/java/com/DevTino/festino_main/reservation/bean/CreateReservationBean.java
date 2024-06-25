package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.CheckReservationDAOBean;
import com.DevTino.festino_main.reservation.bean.small.CreateReservationDAOBean;
import com.DevTino.festino_main.reservation.bean.small.SaveReservationDAOBean;
import com.DevTino.festino_main.reservation.domain.DTO.RequestCreateReservationDTO;
import com.DevTino.festino_main.reservation.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateReservationBean {
    CheckReservationDAOBean checkReservationDAOBean;
    CreateReservationDAOBean createReservationDAOBean;
    SaveReservationDAOBean saveReservationDAOBean;

    @Autowired
    public CreateReservationBean(CheckReservationDAOBean checkReservationDAOBean, CreateReservationDAOBean createReservationDAOBean, SaveReservationDAOBean saveReservationDAOBean) {
        this.checkReservationDAOBean = checkReservationDAOBean;
        this.createReservationDAOBean = createReservationDAOBean;
        this.saveReservationDAOBean = saveReservationDAOBean;
    }

    public UUID exec(RequestCreateReservationDTO requestCreateReservationDTO) {
        // 해당 학과에 예약한 기록이 없을 경우
        // 날짜도 고려?
        if(checkReservationDAOBean.exec(requestCreateReservationDTO)) {
            Reservation createReservation = CreateReservationDAOBean.exec(requestCreateReservationDTO);
            saveReservationDAOBean.exec(createReservation);

            return createReservation.getReservationId();
        }

        return null;
    }
}
