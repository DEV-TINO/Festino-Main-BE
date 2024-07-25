package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.booth.domain.entity.NightBoothDAO;
import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import com.DevTino.festino_main.reservation.domain.ReservationEnum;
import com.DevTino.festino_main.reservation.repository.ReservationRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateReservationDAOBean {
    CheckReservationDAODateFieldBean checkReservationDAODateFieldBean;
    ReservationRepositoryJPA reservationRepositoryJPA;

    @Autowired
    public CreateReservationDAOBean(CheckReservationDAODateFieldBean checkReservationDAODateFieldBean, ReservationRepositoryJPA reservationRepositoryJPA) {
        this.checkReservationDAODateFieldBean = checkReservationDAODateFieldBean;
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    // RequestReservationSaveDTO -> ReservationDAO 변경
    public ReservationDAO exec(NightBoothDAO nightBoothDAO, RequestReservationSaveDTO requestReservationSaveDTO) {
        Integer date = checkReservationDAODateFieldBean.exec(nightBoothDAO);
        ReservationDAO reservationDAO = reservationRepositoryJPA.findFirstByDateOrderByReservationNumDesc(date);
        Integer reservationNum = reservationDAO == null ? 1 : reservationDAO.getReservationNum() + 1;

        return ReservationDAO.builder()
                .reservationId(UUID.randomUUID())
                .userName(requestReservationSaveDTO.getUserName())
                .phoneNum(requestReservationSaveDTO.getPhoneNum())
                .boothId(requestReservationSaveDTO.getBoothId())
                .personCount(requestReservationSaveDTO.getPersonCount())
                .date(date)
                .reservationNum(reservationNum)
                .reservationType(ReservationEnum.RESERVE)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }
}
