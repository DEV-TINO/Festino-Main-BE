package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.CreateReservationDTOBean;
import com.DevTino.festino_main.reservation.bean.small.GetReservationByUserNameAndPhoneNumDAOBean;
import com.DevTino.festino_main.reservation.model.DTO.RequestReservationGetDTO;
import com.DevTino.festino_main.reservation.model.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.model.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetReservationBean {
    GetReservationByUserNameAndPhoneNumDAOBean getReservationByUserNameAndPhoneNumDAOBean;
    CreateReservationDTOBean createReservationDTOBean;

    @Autowired
    public GetReservationBean(GetReservationByUserNameAndPhoneNumDAOBean getReservationByUserNameAndPhoneNumDAOBean, CreateReservationDTOBean createReservationDTOBean) {
        this.getReservationByUserNameAndPhoneNumDAOBean = getReservationByUserNameAndPhoneNumDAOBean;
        this.createReservationDTOBean = createReservationDTOBean;
    }

    // 예약 조회
    public ResponseReservationGetDTO exec(RequestReservationGetDTO requestReservationGetDTO) {
        // 예약된 내역이 있는지 조회
        ReservationDAO reservationDAO = getReservationByUserNameAndPhoneNumDAOBean.exec(requestReservationGetDTO.getUserName(), requestReservationGetDTO.getPhoneNum());

        // 데이터가 없을 경우 null, 있을 경우 ResponseReservationGetDTO로 변환해서 반환
        return reservationDAO == null ? null : createReservationDTOBean.exec(reservationDAO);
    }
}