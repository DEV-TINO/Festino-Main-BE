package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.GetReservationByPhoneNumDAOBean;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckReservationPhoneNumBean {

    GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean;

    @Autowired
    public CheckReservationPhoneNumBean(GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean) {
        this.getReservationByPhoneNumDAOBean = getReservationByPhoneNumDAOBean;
    }

    // 예약된 전화번호인지 확인
    public boolean exec(String phoneNum) {

        ReservationDAO reservationDAO = getReservationByPhoneNumDAOBean.exec(phoneNum);
        return reservationDAO != null;
    }
}
