package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.bean.small.SaveNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.reservation.bean.small.CreateReservationDAOBean;
import com.DevTino.festino_main.reservation.bean.small.GetReservationByUserNameAndPhoneNumDAOBean;
import com.DevTino.festino_main.reservation.bean.small.SaveReservationDAOBean;
import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveReservationBean {
    GetReservationByUserNameAndPhoneNumDAOBean getReservationByUserNameAndPhoneNumDAOBean;
    CreateReservationDAOBean createReservationDAOBean;
    SaveReservationDAOBean saveReservationDAOBean;
    GetNightBoothDAOBean getNightBoothDAOBean;
    SaveNightBoothDAOBean saveNightBoothDAOBean;

    @Autowired
    public SaveReservationBean(GetReservationByUserNameAndPhoneNumDAOBean getReservationByUserNameAndPhoneNumDAOBean, CreateReservationDAOBean createReservationDAOBean, SaveReservationDAOBean saveReservationDAOBean, GetNightBoothDAOBean getNightBoothDAOBean, SaveNightBoothDAOBean saveNightBoothDAOBean) {
        this.getReservationByUserNameAndPhoneNumDAOBean = getReservationByUserNameAndPhoneNumDAOBean;
        this.createReservationDAOBean = createReservationDAOBean;
        this.saveReservationDAOBean = saveReservationDAOBean;
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.saveNightBoothDAOBean = saveNightBoothDAOBean;
    }

    // 예약 등록
    public UUID exec(RequestReservationSaveDTO requestReservationSaveDTO) {
        // 이전 예약 기록이 있을 경우 예약이 불가능
        if(getReservationByUserNameAndPhoneNumDAOBean.exec(requestReservationSaveDTO.getUserName(), requestReservationSaveDTO.getPhoneNum()) != null) {
            return null;
        }

        // 예약을 등록한 뒤 reservationId 반환
        ReservationDAO createReservationDAO = createReservationDAOBean.exec(requestReservationSaveDTO);

        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(requestReservationSaveDTO.getBoothId());
        nightBoothDAO.setTotalReservationNum(nightBoothDAO.getTotalReservationNum() + 1);

        saveReservationDAOBean.exec(createReservationDAO);
        saveNightBoothDAOBean.exec(nightBoothDAO);

        return createReservationDAO.getReservationId();
    }
}
