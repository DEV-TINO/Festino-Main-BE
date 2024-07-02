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

    // ReservationDAO -> ResponseReservationGetDTO로 변경
    public ResponseReservationGetDTO exec(ReservationDAO reservationDAO) {
        ResponseReservationGetDTO responseReservationGetDTO = new ResponseReservationGetDTO();
        responseReservationGetDTO.setReservationId(reservationDAO.getReservationId());
        responseReservationGetDTO.setPersonCount(reservationDAO.getPersonCount());
        responseReservationGetDTO.setBoothId(reservationDAO.getBoothId());
        responseReservationGetDTO.setTotalTeamCount(getReservationsByBoothIdAndCreateAtLessThanDAOBean.exec(reservationDAO).size());
        // priorityNum 추가 예정

        return responseReservationGetDTO;
    }
}