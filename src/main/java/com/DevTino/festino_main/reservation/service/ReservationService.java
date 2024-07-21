package com.DevTino.festino_main.reservation.service;

import com.DevTino.festino_main.reservation.bean.GetReservationBean;
import com.DevTino.festino_main.reservation.bean.SaveReservationBean;
import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class ReservationService {
    GetReservationBean getReservationBean;
    SaveReservationBean saveReservationBean;

    @Autowired
    public ReservationService(SaveReservationBean saveReservationBean, GetReservationBean getReservationBean) {
        this.saveReservationBean = saveReservationBean;
        this.getReservationBean = getReservationBean;
    }

    // 예약 등록
    public ResponseReservationSaveDTO saveReservation(RequestReservationSaveDTO requestReservationSaveDTO) throws IOException {
        return saveReservationBean.exec(requestReservationSaveDTO);
    }

    // 예약 조회
    public ResponseReservationGetDTO getReservation(String userName, String phoneNum) {
        return getReservationBean.exec(userName, phoneNum);
    }
}
