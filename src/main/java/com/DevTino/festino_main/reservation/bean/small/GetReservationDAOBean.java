package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.model.ReservationDAO;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetReservationDAOBean {
    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public GetReservationDAOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    public ReservationDAO exec(String userName, String phoneNum) {
        return reservationRepositoryJPA.findByUserNameAndPhoneNumAndIsDeleted(userName, phoneNum, false);
    }
}
