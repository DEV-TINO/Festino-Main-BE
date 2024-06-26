package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.model.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.model.ReservationDAO;
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
        ReservationDAO reservationDAO = reservationRepositoryJPA.findByUserNameAndPhoneNumAndIsDeleted(requestReservationSaveDTO.getUserName(), requestReservationSaveDTO.getPhoneNum(), false);

        return reservationDAO == null;
    }
}