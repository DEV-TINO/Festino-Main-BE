package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.ReservationDAO;
import com.DevTino.festino_main.reservation.domain.ReservationEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateReservationDAOBean {

    // RequestReservationSaveDTO -> ReservationDAO 변경
    public ReservationDAO exec(Integer date, RequestReservationSaveDTO requestReservationSaveDTO) {

        return ReservationDAO.builder()
                .reservationId(UUID.randomUUID())
                .userName(requestReservationSaveDTO.getUserName())
                .phoneNum(requestReservationSaveDTO.getPhoneNum())
                .boothId(requestReservationSaveDTO.getBoothId())
                .personCount(requestReservationSaveDTO.getPersonCount())
                .date(date)
                .reservationType(ReservationEnum.RESERVE)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }
}
