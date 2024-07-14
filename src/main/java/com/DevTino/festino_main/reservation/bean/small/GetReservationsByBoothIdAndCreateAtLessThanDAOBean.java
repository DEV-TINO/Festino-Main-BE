package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReservationsByBoothIdAndCreateAtLessThanDAOBean {
    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public GetReservationsByBoothIdAndCreateAtLessThanDAOBean(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    // 특정 부스에서 취소되지 않은 데이터들을 내림차순으로 정렬해서 반환
    public List<ReservationDAO> exec(ReservationDAO reservationDAO) {
        return reservationRepositoryJPA.findAllByBoothIdAndIsCancelAndCreateAtLessThan(reservationDAO.getBoothId(), false, reservationDAO.getCreateAt());
    }
}
