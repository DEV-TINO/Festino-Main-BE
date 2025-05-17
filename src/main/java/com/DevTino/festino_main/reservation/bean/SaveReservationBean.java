package com.DevTino.festino_main.reservation.bean;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.booth.bean.small.GetNightBoothDAOBean;
import com.DevTino.festino_main.booth.bean.small.SaveNightBoothDAOBean;
import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.message.bean.SaveReservationSendMessageBean;
import com.DevTino.festino_main.reservation.bean.small.*;
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
    CheckReservationDAOBean checkReservationDAOBean;

    @Autowired
    public SaveReservationBean(GetReservationByPhoneNumDAOBean getReservationByPhoneNumDAOBean, GetNightBoothDAOBean getNightBoothDAOBean, CreateReservationDAOBean createReservationDAOBean, SaveReservationDAOBean saveReservationDAOBean, CheckReservationDAODateFieldBean checkReservationDAODateFieldBean, SaveNightBoothDAOBean saveNightBoothDAOBean, SaveReservationSendMessageBean saveReservationSendMessageBean, CheckReservationDAOBean checkReservationDAOBean) {
        this.getReservationByPhoneNumDAOBean = getReservationByPhoneNumDAOBean;
        this.getNightBoothDAOBean = getNightBoothDAOBean;
        this.createReservationDAOBean = createReservationDAOBean;
        this.saveReservationDAOBean = saveReservationDAOBean;
        this.checkReservationDAODateFieldBean = checkReservationDAODateFieldBean;
        this.saveNightBoothDAOBean = saveNightBoothDAOBean;
        this.saveReservationSendMessageBean = saveReservationSendMessageBean;
        this.checkReservationDAOBean = checkReservationDAOBean;
    }

    // 예약 등록
    public ResponseReservationSaveDTO exec(RequestReservationSaveDTO requestReservationSaveDTO) throws IOException {

        // 부스 정보 조회
        NightBoothDAO nightBoothDAO = getNightBoothDAOBean.exec(requestReservationSaveDTO.getBoothId());

        // 부스가 닫혀 있거나 예약 불가할 경우 예외 발생
        if (!nightBoothDAO.getIsOpen()) throw new ServiceException(ExceptionEnum.BOOTH_CLOSED);
        if (!nightBoothDAO.getIsReservation()) throw new ServiceException(ExceptionEnum.RESERVATION_DISABLED);

        // 이전 예약 기록이 있을 경우 예약이 불가능
        // 예약은 전화번호, RELEASE 기준으로 함
        // 전화번호, RELEASE 가 같은 경우 기존 예약을 취소하고 진행함

        // 이미 있는 경우 덮어쓰기
        if(checkReservationDAOBean.exec(requestReservationSaveDTO.getPhoneNum())) {

            ReservationDAO reservationDAO = getReservationByPhoneNumDAOBean.exec(requestReservationSaveDTO.getPhoneNum());

            reservationDAO.setReservationType(ReservationEnum.CANCEL);
            reservationDAO.setUpdateAt(DateTimeUtils.nowZone());

            NightBoothDAO oldNightBoothDAO = getNightBoothDAOBean.exec(reservationDAO.getBoothId());
            oldNightBoothDAO.setTotalReservationNum(oldNightBoothDAO.getTotalReservationNum() - 1);

            saveReservationDAOBean.exec(reservationDAO);
            saveNightBoothDAOBean.exec(oldNightBoothDAO);
        }

        // 예약 당일이 언제인지
        Integer date = checkReservationDAODateFieldBean.exec(nightBoothDAO);

        // 예약을 등록한 뒤 reservationId 반환
        ReservationDAO createReservationDAO = createReservationDAOBean.exec(date, requestReservationSaveDTO);

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
