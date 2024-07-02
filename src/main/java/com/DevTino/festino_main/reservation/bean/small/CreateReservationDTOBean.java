package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.model.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.model.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateReservationDTOBean {
    GetReservationsByBoothIdAndCreateAtLessThanDAOBean getReservationsByBoothIdAndCreateAtLessThanDAOBean;

    @Autowired
    public CreateReservationDTOBean(GetReservationsByBoothIdAndCreateAtLessThanDAOBean getReservationsByBoothIdAndCreateAtLessThanDAOBean) {
        this.getReservationsByBoothIdAndCreateAtLessThanDAOBean = getReservationsByBoothIdAndCreateAtLessThanDAOBean;
    }

    // ReservationDAO -> ResponseReservationGetDTO 변경
    public ResponseReservationGetDTO exec(ReservationDAO reservationDAO) {
        return ResponseReservationGetDTO.builder()
                .reservationId(reservationDAO.getReservationId())
                .personCount(reservationDAO.getPersonCount())
                .boothId(reservationDAO.getBoothId())
                .totalTeamCount(getReservationsByBoothIdAndCreateAtLessThanDAOBean.exec(reservationDAO).size())
                // priorityNum 추가 예정
                .build();
    }
}