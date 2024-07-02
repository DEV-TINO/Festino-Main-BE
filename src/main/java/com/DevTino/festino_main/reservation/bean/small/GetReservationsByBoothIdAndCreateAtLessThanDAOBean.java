package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.model.ReservationDAO;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReservationsByBoothIdAndCreateAtLessThanDAOBean {
    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public GetReservationsByBoothIdAndCreateAtLessThanDAOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    public List<ReservationDAO> exec(ReservationDAO reservationDAO) {
        return reservationRepositoryJPA.findAllByBoothIdAndIsDeletedAndCreateAtLessThan(reservationDAO.getBoothId(), false, reservationDAO.getCreateAt());
    }
}
