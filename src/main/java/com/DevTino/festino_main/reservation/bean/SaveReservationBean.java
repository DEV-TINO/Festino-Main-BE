package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.CreateReservationDAOBean;
import com.DevTino.festino_main.reservation.bean.small.GetReservationDAOBean;
import com.DevTino.festino_main.reservation.bean.small.SaveReservationDAOBean;
import com.DevTino.festino_main.reservation.model.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.model.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveReservationBean {
    GetReservationDAOBean getReservationDAOBean;
    CreateReservationDAOBean createReservationDAOBean;
    SaveReservationDAOBean saveReservationDAOBean;

    @Autowired
    public SaveReservationBean(GetReservationDAOBean getReservationDAOBean, CreateReservationDAOBean createReservationDAOBean, SaveReservationDAOBean saveReservationDAOBean) {
        this.getReservationDAOBean = getReservationDAOBean;
        this.createReservationDAOBean = createReservationDAOBean;
        this.saveReservationDAOBean = saveReservationDAOBean;
    }

    // 예약 등록하기
    public UUID exec(RequestReservationSaveDTO requestReservationSaveDTO) {
        // 이전 예약 기록이 없을 경우
        if(getReservationDAOBean.exec(requestReservationSaveDTO.getUserName(), requestReservationSaveDTO.getPhoneNum()) == null) {
            ReservationDAO createReservationDAO = CreateReservationDAOBean.exec(requestReservationSaveDTO);
            saveReservationDAOBean.exec(createReservationDAO);

            return createReservationDAO.getReservationId();
        }

        // 이전 예약 기록이 있을 경우 예약이 불가능
        return null;
    }
}
