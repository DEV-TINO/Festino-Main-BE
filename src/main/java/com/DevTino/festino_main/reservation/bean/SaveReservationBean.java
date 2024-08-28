package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.bean.small.SaveNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.message.bean.SaveReservationSendMessageBean;
import com.DevTino.festino_main.reservation.bean.small.CheckReservationDAODateFieldBean;
import com.DevTino.festino_main.reservation.bean.small.CreateReservationDAOBean;
import com.DevTino.festino_main.reservation.bean.small.GetReservationByPhoneNumDAOBean;
import com.DevTino.festino_main.reservation.bean.small.SaveReservationDAOBean;
import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import com.DevTino.festino_main.reservation.domain.ReservationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SaveReservationBean {

    GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean;
    GetNightBoothDAOBean getNightBoothDAOBean;
    CreateReservationDAOBean createReservationDAOBean;
    SaveReservationDAOBean saveReservationDAOBean;
    CheckReservationDAODateFieldBean checkReservationDAODateFieldBean;
    SaveNightBoothDAOBean saveNightBoothDAOBean;
    SaveReservationSendMessageBean saveReservationSendMessageBean;

    @Autowired
    public SaveReservationBean(GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean, GetNightBoothDAOBean getNightBoothDAOBean, CreateReservationDAOBean createReservationDAOBean, SaveReservationDAOBean saveReservationDAOBean, CheckReservationDAODateFieldBean checkReservationDAODateFieldBean, SaveNightBoothDAOBean saveNightBoothDAOBean, SaveReservationSendMessageBean saveReservationSendMessageBean) {
        this.getReservationByPhoneNumDAOBean = getReservationByPhoneNumDAOBean;
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.createReservationDAOBean = createReservationDAOBean;
        this.saveReservationDAOBean = saveReservationDAOBean;
        this.checkReservationDAODateFieldBean = checkReservationDAODateFieldBean;
        this.saveNightBoothDAOBean = saveNightBoothDAOBean;
        this.saveReservationSendMessageBean = saveReservationSendMessageBean;
    }

    // 예약 등록
    public ResponseReservationSaveDTO exec(RequestReservationSaveDTO requestReservationSaveDTO) throws IOException {
        // 부스가 운영중인지, 예약가능한지 여부 확인하는 예외 처리
        NightBoothDAO exceptionBoothDAO = getNightBoothDAOBean.exec(requestReservationSaveDTO.getBoothId());
        if(!exceptionBoothDAO.getIsOpen() || !exceptionBoothDAO.getIsReservation()) return null;

        // 이전 예약 기록이 있을 경우 예약이 불가능
        // 예약은 전화번호, RELEASE 기준으로 함
        // 전화번호, RELEASE 가 같은 경우 기존 예약을 취소하고 진행함
        ReservationDAO reservationDAO = getReservationByPhoneNumDAOBean.exec(requestReservationSaveDTO.getPhoneNum());

        // 이미 있는 경우 덮어쓰기
        if(reservationDAO != null) {
            reservationDAO.setReservationType(ReservationEnum.CANCEL);
            reservationDAO.setUpdateAt(DateTimeUtils.nowZone());

            NightBoothDAO oldNightBoothDAO = getNightBoothDAOBean.exec(reservationDAO.getBoothId());
            oldNightBoothDAO.setTotalReservationNum(oldNightBoothDAO.getTotalReservationNum() - 1);

            saveReservationDAOBean.exec(reservationDAO);
            saveNightBoothDAOBean.exec(oldNightBoothDAO);
        }

        // 야간부스 전체 예약수 관리를 위해 야간부스 정보를 가져옴
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(requestReservationSaveDTO.getBoothId());
        if (nightBoothDAO == null) return null;

        // 예약 당일이 언제인지
        Integer date = checkReservationDAODateFieldBean.exec(nightBoothDAO);

        // 예약을 등록한 뒤 reservationId 반환
        ReservationDAO createReservationDAO = createReservationDAOBean.exec(date, requestReservationSaveDTO);
        if (createReservationDAO == null) return null;

        nightBoothDAO.setTotalReservationNum(nightBoothDAO.getTotalReservationNum() + 1);

        saveReservationDAOBean.exec(createReservationDAO);
        saveNightBoothDAOBean.exec(nightBoothDAO);


        // 메세지 전송
        String check = saveReservationSendMessageBean.exec(requestReservationSaveDTO.getBoothId(), createReservationDAO.getPhoneNum(), createReservationDAO.getUserName());

        return ResponseReservationSaveDTO.builder()
                .reservationId(createReservationDAO.getReservationId())
                .messageStatus(check)
                .build();
    }
}
