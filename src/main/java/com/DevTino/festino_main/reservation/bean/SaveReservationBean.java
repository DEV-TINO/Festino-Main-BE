package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.reservation.bean.small.CheckReservationDAOBean;
import com.DevTino.festino_main.reservation.bean.small.CreateReservationDAOBean;
import com.DevTino.festino_main.reservation.bean.small.SaveReservationDAOBean;
import com.DevTino.festino_main.reservation.model.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.model.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveReservationBean {
    CheckReservationDAOBean checkReservationDAOBean;
    CreateReservationDAOBean createReservationDAOBean;
    SaveReservationDAOBean saveReservationDAOBean;

    @Autowired
    public SaveReservationBean(CheckReservationDAOBean checkReservationDAOBean, CreateReservationDAOBean createReservationDAOBean, SaveReservationDAOBean saveReservationDAOBean) {
        this.checkReservationDAOBean = checkReservationDAOBean;
        this.createReservationDAOBean = createReservationDAOBean;
        this.saveReservationDAOBean = saveReservationDAOBean;
    }

    // 예약 등록하기
    public UUID exec(RequestReservationSaveDTO requestReservationSaveDTO) {
        // 이전 예약 기록이 없을 경우
        if(checkReservationDAOBean.exec(requestReservationSaveDTO)) {
            ReservationDAO createReservationDAO = CreateReservationDAOBean.exec(requestReservationSaveDTO);
            saveReservationDAOBean.exec(createReservationDAO);

            return createReservationDAO.getReservationId();
        }

        // 이전 예약 기록이 있을 경우 예약이 불가능
        return null;
    }
}
