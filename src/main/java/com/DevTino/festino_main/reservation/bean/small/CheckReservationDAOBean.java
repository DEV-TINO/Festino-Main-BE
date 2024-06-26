package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.DTO.RequestCreateReservationDTO;
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

    public boolean exec(RequestCreateReservationDTO requestCreateReservationDTO) {
        Reservation reservation = reservationRepositoryJPA.findByUserNameAndPhoneNum(requestCreateReservationDTO.getUserName(), requestCreateReservationDTO.getPhoneNum());

        return reservation == null;
    }
}