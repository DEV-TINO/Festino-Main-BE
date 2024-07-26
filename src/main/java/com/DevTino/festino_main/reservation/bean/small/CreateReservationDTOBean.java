package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import com.DevTino.festino_main.reservation.domain.ReservationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateReservationDTOBean {

    GetNightBoothDAOBean getNightBoothDAOBean;

    @Autowired
    public CreateReservationDTOBean(GetNightBoothDAOBean getNightBoothDAOBean) {
        this.getNightBoothDAOBean = getNightBoothDAOBean;
    }

    // ReservationDAO -> ResponseReservationGetDTO 변경
    public ResponseReservationGetDTO exec(ReservationDAO reservationDAO, List<ReservationDAO> reservationDAOList) {

        // 예약 번호 매핑
        int reservationNum = 0;
        int totalTeamCount = 0;

        for (ReservationDAO getReservationDAO : reservationDAOList){
            reservationNum++;

            if (getReservationDAO.getReservationType().equals(ReservationEnum.RESERVE)) totalTeamCount++;

            if (getReservationDAO.getReservationId().equals(reservationDAO.getReservationId())) break;
        }

        // 이미 부스로 전체 다 가져왔지
        // 그러면 나 포함 갯수를 세자 RELSASE

        return ResponseReservationGetDTO.builder()
                .reservationId(reservationDAO.getReservationId())
                .personCount(reservationDAO.getPersonCount())
                .boothId(reservationDAO.getBoothId())
                .adminName(getNightBoothDAOBean.exec(reservationDAO.getBoothId()).getAdminName())
                .totalTeamCount(totalTeamCount)
                .date(reservationDAO.getDate())
                .reservationNum(reservationNum)
                .reservationType(reservationDAO.getReservationType())
                .build();
    }
}