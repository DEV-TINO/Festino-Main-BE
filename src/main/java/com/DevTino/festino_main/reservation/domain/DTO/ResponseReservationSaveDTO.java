package com.DevTino.festino_main.reservation.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseReservationSaveDTO {
    UUID reservationId;
    String messageStatus;
}