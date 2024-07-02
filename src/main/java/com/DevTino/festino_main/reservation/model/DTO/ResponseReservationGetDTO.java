package com.DevTino.festino_main.reservation.model.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseReservationGetDTO {
    UUID reservationId;
    Integer personCount;
    UUID boothId;
    Integer totalTeamCount;
}