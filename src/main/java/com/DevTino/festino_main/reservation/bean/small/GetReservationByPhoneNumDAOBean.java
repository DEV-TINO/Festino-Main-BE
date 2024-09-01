package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import com.DevTino.festino_main.reservation.domain.ReservationEnum;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetReservationByPhoneNumDAOBean {
    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public GetReservationByPhoneNumDAOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    // 이름, 번호, 예약 여부로 예약 내역 확인
    @Transactional(readOnly = true)
    public ReservationDAO exec(String userName, String phoneNum) {
        return reservationRepositoryJPA.findByUserNameAndPhoneNumAndReservationType(userName, phoneNum, ReservationEnum.RESERVE);
    }

    // 번호, 예약 여부로 예약 내역 확인
    @Transactional(readOnly = true)
    public ReservationDAO exec(String phoneNum) {
        return reservationRepositoryJPA.findByPhoneNumAndReservationType(phoneNum, ReservationEnum.RESERVE);
    }
}
