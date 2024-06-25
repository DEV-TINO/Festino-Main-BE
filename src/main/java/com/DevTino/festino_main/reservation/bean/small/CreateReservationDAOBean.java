package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.domain.DTO.RequestCreateReservationDTO;
import com.DevTino.festino_main.reservation.domain.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateReservationDAOBean {

    public static Reservation exec(RequestCreateReservationDTO requestCreateReservationDTO) {
        return Reservation.builder()
                .reservationId(UUID.randomUUID())
                .userName(requestCreateReservationDTO.getUserName())
                .phoneNum(requestCreateReservationDTO.getPhoneNum())
                .boothId(requestCreateReservationDTO.getBoothId())
                .personCount(requestCreateReservationDTO.getPersonCount())
                .createAt(LocalDateTime.now())
                .build();
    }
}
