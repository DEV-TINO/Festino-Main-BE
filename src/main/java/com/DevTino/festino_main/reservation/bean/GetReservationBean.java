package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.GetReservationDAOBean;
import com.DevTino.festino_main.reservation.model.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetReservationBean {
    GetReservationDAOBean getReservationDAOBean;

    @Autowired
    public GetReservationBean(GetReservationDAOBean getReservationDAOBean) {
        this.getReservationDAOBean = getReservationDAOBean;
    }

    public ReservationDAO exec(String phoneNum) {
        return getReservationDAOBean.exec(phoneNum);
    }
}
