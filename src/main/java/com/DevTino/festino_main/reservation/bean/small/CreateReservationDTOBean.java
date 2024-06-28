package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.model.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.model.ReservationDAO;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateReservationDTOBean {
    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public CreateReservationDTOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    // ReservationDAO -> ResponseReservationGetDTO로 변경
    public ResponseReservationGetDTO exec(ReservationDAO reservationDAO) {
        ResponseReservationGetDTO responseReservationGetDTO = new ResponseReservationGetDTO();
        responseReservationGetDTO.setReservationId(reservationDAO.getReservationId());
        responseReservationGetDTO.setPersonCount(reservationDAO.getPersonCount());
        responseReservationGetDTO.setBoothId(reservationDAO.getBoothId());
        responseReservationGetDTO.setTotalTeamCount(reservationRepositoryJPA.findAllByBoothIdAndCreateAtLessThan(reservationDAO.getBoothId(), reservationDAO.getCreateAt()).size());

        return responseReservationGetDTO;
    }
}