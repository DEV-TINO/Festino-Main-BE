package com.DevTino.festino_main.reservation.domain.DTO;

import com.DevTino.festino_main.reservation.domain.ReservationEnum;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseReservationGetDTO {
    UUID reservationId;
    Integer personCount;
    UUID boothId;
    String adminName;
    Integer totalTeamCount;
    Integer date;
    Integer reservationNum;
    ReservationEnum reservationType;
}