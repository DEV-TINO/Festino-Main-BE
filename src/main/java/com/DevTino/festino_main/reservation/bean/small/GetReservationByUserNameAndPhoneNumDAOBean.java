package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetReservationByUserNameAndPhoneNumDAOBean {
    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public GetReservationByUserNameAndPhoneNumDAOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    // 취소되지 않은 데이터 중 이름과 번호로 예약 내역 확인
    public ReservationDAO exec(String userName, String phoneNum) {
        return reservationRepositoryJPA.findByUserNameAndPhoneNumAndIsCancel(userName, phoneNum, false);
    }
}
