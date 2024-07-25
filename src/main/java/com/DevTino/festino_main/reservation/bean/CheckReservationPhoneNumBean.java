package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.GetReservationDetailDAOBean;
import com.DevTino.festino_main.reservation.domain.ReservationDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckReservationPhoneNumBean {

    GetReservationDetailDAOBean getReservationDetailBean;

    @Autowired
    public CheckReservationPhoneNumBean(GetReservationDetailDAOBean getReservationDetailBean) {
        this.getReservationDetailBean = getReservationDetailBean;
    }
    
    // 예약된 전화번호인지 확인
    public boolean exec(String phoneNum) {

        ReservationDetailDAO reservationDetailDAO = getReservationDetailBean.exec(phoneNum);
        return reservationDetailDAO != null;
    }
}
