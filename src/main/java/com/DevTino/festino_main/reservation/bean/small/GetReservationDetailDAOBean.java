package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.ReservationDetailDAO;
import com.DevTino.festino_main.reservation.repository.ReservationDetailRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetReservationDetailDAOBean {

    ReservationDetailRepositoryJPA reservationDetailRepositoryJPA;

    @Autowired
    public GetReservationDetailDAOBean(ReservationDetailRepositoryJPA reservationDetailRepositoryJPA) {
        this.reservationDetailRepositoryJPA = reservationDetailRepositoryJPA;
    }

    // 전화번호로 예약된 내역 조회
    public ReservationDetailDAO exec(String phoneNum) {
        return reservationDetailRepositoryJPA.findByPhoneNum(phoneNum);
    }
}
