package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.CreateReservationDTOBean;
import com.DevTino.festino_main.reservation.bean.small.GetReservationByPhoneNumDAOBean;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetReservationBean {

    GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean;
    CreateReservationDTOBean createReservationDTOBean;

    @Autowired
    public GetReservationBean(GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean, CreateReservationDTOBean createReservationDTOBean) {
        this.getReservationByPhoneNumDAOBean = getReservationByPhoneNumDAOBean;
        this.createReservationDTOBean = createReservationDTOBean;
    }


    // 예약 조회
    public ResponseReservationGetDTO exec(String phoneNum) {

        // 예약된 내역이 있는지 조회
        ReservationDAO reservationDAO = getReservationByPhoneNumDAOBean.exec(phoneNum);

        // 데이터가 없을 경우 null, 있을 경우 ResponseReservationGetDTO로 변환해서 반환
        return reservationDAO == null ? null : createReservationDTOBean.exec(reservationDAO);
    }
}
