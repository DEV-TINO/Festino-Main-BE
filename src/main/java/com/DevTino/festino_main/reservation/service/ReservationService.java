package com.DevTino.festino_main.reservation.service;

import com.DevTino.festino_main.reservation.bean.CheckReservationPhoneNumBean;
import com.DevTino.festino_main.reservation.bean.GetReservationBean;
import com.DevTino.festino_main.reservation.bean.SaveReservationBean;
import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReservationService {
    GetReservationBean getReservationBean;
    SaveReservationBean saveReservationBean;
    CheckReservationPhoneNumBean checkReservationPhoneNumBean;

    @Autowired
    public ReservationService(SaveReservationBean saveReservationBean, GetReservationBean getReservationBean, CheckReservationPhoneNumBean checkReservationPhoneNumBean) {
        this.saveReservationBean = saveReservationBean;
        this.getReservationBean = getReservationBean;
        this.checkReservationPhoneNumBean = checkReservationPhoneNumBean;
    }

    // 예약 등록
    public ResponseReservationSaveDTO saveReservation(RequestReservationSaveDTO requestReservationSaveDTO) throws IOException {
        return saveReservationBean.exec(requestReservationSaveDTO);
    }

    // 예약 조회
    public ResponseReservationGetDTO getReservation(String phoneNum) {
        return getReservationBean.exec(phoneNum);
    }

    // 전화번호 중복 조회
    public boolean checkReservationPhoneNum(String phoneNum) {
        return checkReservationPhoneNumBean.exec(phoneNum);
    }
}
