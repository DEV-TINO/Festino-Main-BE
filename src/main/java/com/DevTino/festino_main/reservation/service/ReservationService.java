package com.DevTino.festino_main.reservation.service;

import com.DevTino.festino_main.reservation.bean.CreateReservationBean;
import com.DevTino.festino_main.reservation.domain.DTO.RequestCreateReservationDTO;
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

    public UUID createReservation(RequestCreateReservationDTO requestCreateReservationDTO) {
        return createReservationBean.exec(requestCreateReservationDTO);
    }
}
