package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class GetReservationByBoothIdDAOBean {

    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public GetReservationByBoothIdDAOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    // 부스 아이디로 예약 내역 조회
    @Transactional(readOnly = true)
    public List<ReservationDAO> exec(UUID boothId) {
        return reservationRepositoryJPA.findAllByBoothIdOrderByReservationNumAsc(boothId);
    }
}
