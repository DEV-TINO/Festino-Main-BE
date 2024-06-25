package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.Reservation;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveReservationDAOBean {
    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public SaveReservationDAOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    public void exec(Reservation reservation) {
        reservationRepositoryJPA.save(reservation);
    }
}