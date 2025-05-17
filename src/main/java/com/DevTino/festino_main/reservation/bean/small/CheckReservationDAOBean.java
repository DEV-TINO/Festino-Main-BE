package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.ReservationEnum;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CheckReservationDAOBean {

    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public CheckReservationDAOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }



    // 번호, 예약 여부로 예약 내역 확인
    @Transactional(readOnly = true)
    public boolean exec(String phoneNum) {
        return reservationRepositoryJPA.existsByPhoneNumAndReservationType(phoneNum, ReservationEnum.RESERVE);
    }

}
