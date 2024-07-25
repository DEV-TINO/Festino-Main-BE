package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.bean.small.SaveNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.message.bean.SaveReservationSendMessageBean;
import com.DevTino.festino_main.reservation.bean.small.CreateReservationDAOBean;
import com.DevTino.festino_main.reservation.bean.small.GetReservationByPhoneNumDAOBean;
import com.DevTino.festino_main.reservation.bean.small.SaveReservationDAOBean;
import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SaveReservationBean {
    GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean;
    CreateReservationDAOBean createReservationDAOBean;
    SaveReservationDAOBean saveReservationDAOBean;
    GetNightBoothDAOBean getNightBoothDAOBean;
    SaveNightBoothDAOBean saveNightBoothDAOBean;
    SaveReservationSendMessageBean saveReservationSendMessageBean;

    @Autowired
    public SaveReservationBean(GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean, CreateReservationDAOBean createReservationDAOBean, SaveReservationDAOBean saveReservationDAOBean, GetNightBoothDAOBean getNightBoothDAOBean, SaveNightBoothDAOBean saveNightBoothDAOBean, SaveReservationSendMessageBean saveReservationSendMessageBean) {
        this.getReservationByPhoneNumDAOBean = getReservationByPhoneNumDAOBean;
        this.createReservationDAOBean = createReservationDAOBean;
        this.saveReservationDAOBean = saveReservationDAOBean;
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.saveNightBoothDAOBean = saveNightBoothDAOBean;
        this.saveReservationSendMessageBean = saveReservationSendMessageBean;
    }

    // 예약 등록
    public ResponseReservationSaveDTO exec(RequestReservationSaveDTO requestReservationSaveDTO) throws IOException {

        // 이전 예약 기록이 있을 경우 예약이 불가능
        // 예약은 전화번호, RELEASE 기준으로 함
        // 전화번호, RELEASE 가 같은 경우 기존 예약을 취소하고 진행함
        if(getReservationByPhoneNumDAOBean.exec(requestReservationSaveDTO.getPhoneNum()) != null) {
            return null;
        }

        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(requestReservationSaveDTO.getBoothId());
        if (nightBoothDAO == null) return null;

        // 예약을 등록한 뒤 reservationId 반환
        ReservationDAO createReservationDAO = createReservationDAOBean.exec(nightBoothDAO, requestReservationSaveDTO);
        if (createReservationDAO == null) return null;

        nightBoothDAO.setTotalReservationNum(nightBoothDAO.getTotalReservationNum() + 1);

        saveReservationDAOBean.exec(createReservationDAO);
        saveNightBoothDAOBean.exec(nightBoothDAO);


        // 메세지 전송
        String check = saveReservationSendMessageBean.exec(createReservationDAO.getPhoneNum(), createReservationDAO.getUserName(), nightBoothDAO.getAdminName());

        return ResponseReservationSaveDTO.builder()
                .reservationId(createReservationDAO.getReservationId())
                .messageStatus(check)
                .build();
    }
}
