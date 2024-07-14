package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateReservationDTOBean {
    GetReservationsByBoothIdAndCreateAtLessThanDAOBean getReservationsByBoothIdAndCreateAtLessThanDAOBean;
    GetNightBoothDAOBean getNightBoothDAOBean;

    @Autowired
    public CreateReservationDTOBean(GetReservationsByBoothIdAndCreateAtLessThanDAOBean getReservationsByBoothIdAndCreateAtLessThanDAOBean, GetNightBoothDAOBean getNightBoothDAOBean) {
        this.getReservationsByBoothIdAndCreateAtLessThanDAOBean = getReservationsByBoothIdAndCreateAtLessThanDAOBean;
        this.getNightBoothDAOBean = getNightBoothDAOBean;
    }

    // ReservationDAO -> ResponseReservationGetDTO 변경
    public ResponseReservationGetDTO exec(ReservationDAO reservationDAO) {
        return ResponseReservationGetDTO.builder()
                .reservationId(reservationDAO.getReservationId())
                .personCount(reservationDAO.getPersonCount())
                .boothId(reservationDAO.getBoothId())
                .adminName(getNightBoothDAOBean.exec(reservationDAO.getBoothId()).getAdminName())
                .totalTeamCount(getReservationsByBoothIdAndCreateAtLessThanDAOBean.exec(reservationDAO).size())
                // priorityNum 추가 예정
                .build();
    }
}