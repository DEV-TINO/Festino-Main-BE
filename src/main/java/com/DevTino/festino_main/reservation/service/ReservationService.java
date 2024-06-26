package com.DevTino.festino_main.reservation.service;

import com.DevTino.festino_main.reservation.bean.SaveReservationBean;
import com.DevTino.festino_main.reservation.model.DTO.RequestReservationSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {
    SaveReservationBean saveReservationBean;

    @Autowired
    public ReservationService(SaveReservationBean saveReservationBean) {
        this.saveReservationBean = saveReservationBean;
    }

    // 예약 등록하기
    public UUID createReservation(RequestReservationSaveDTO requestReservationSaveDTO) {
        return saveReservationBean.exec(requestReservationSaveDTO);
    }
}
