package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.reservation.bean.small.GetReservationByPhoneNumDAOBean;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckReservationPhoneNumBean {

    GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean;
    GetNightBoothDAOBean getNightBoothDAOBean;

    @Autowired
    public CheckReservationPhoneNumBean(GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean, GetNightBoothDAOBean getNightBoothDAOBean) {
        this.getReservationByPhoneNumDAOBean = getReservationByPhoneNumDAOBean;
        this.getNightBoothDAOBean = getNightBoothDAOBean;
    }

    // 예약된 전화번호인지 확인
    public String exec(String phoneNum) {

        ReservationDAO reservationDAO = getReservationByPhoneNumDAOBean.exec(phoneNum);

        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(reservationDAO.getBoothId());

        return nightBoothDAO.getAdminName();

    }
}
