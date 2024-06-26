package com.DevTino.festino_main.reservation.service;

import com.DevTino.festino_main.reservation.bean.CreateReservationBean;
import com.DevTino.festino_main.reservation.model.DTO.RequestReservationSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {
    CreateReservationBean createReservationBean;

    @Autowired
    public ReservationService(CreateReservationBean createReservationBean) {
        this.createReservationBean = createReservationBean;
    }

    // 예약 등록하기
    public UUID createReservation(RequestReservationSaveDTO requestReservationSaveDTO) {
        return createReservationBean.exec(requestReservationSaveDTO);
    }
}
