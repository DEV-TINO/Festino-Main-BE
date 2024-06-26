package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.Reservation;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckReservationDAOBean {
    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public CheckReservationDAOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    public boolean exec(RequestReservationSaveDTO requestReservationSaveDTO) {
        Reservation reservation = reservationRepositoryJPA.findByUserNameAndPhoneNum(requestReservationSaveDTO.getUserName(), requestReservationSaveDTO.getPhoneNum());

        return reservation == null;
    }
}