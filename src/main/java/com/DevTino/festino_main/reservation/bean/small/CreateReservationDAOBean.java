package com.DevTino.festino_main.reservation.bean.small;

import com.DevTino.festino_main.reservation.model.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateReservationDAOBean {

    public static Reservation exec(RequestReservationSaveDTO requestReservationSaveDTO) {
        return Reservation.builder()
                .reservationId(UUID.randomUUID())
                .userName(requestReservationSaveDTO.getUserName())
                .phoneNum(requestReservationSaveDTO.getPhoneNum())
                .boothId(requestReservationSaveDTO.getBoothId())
                .personCount(requestReservationSaveDTO.getPersonCount())
                .createAt(LocalDateTime.now())
                .build();
    }
}
