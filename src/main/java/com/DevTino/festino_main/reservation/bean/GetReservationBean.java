package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.GetReservationDAOBean;
import com.DevTino.festino_main.reservation.model.DTO.RequestReservationGetDTO;
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

    public ReservationDAO exec(RequestReservationGetDTO requestReservationGetDTO) {
        return getReservationDAOBean.exec(requestReservationGetDTO.getUserName(), requestReservationGetDTO.getPhoneNum());
    }
}
