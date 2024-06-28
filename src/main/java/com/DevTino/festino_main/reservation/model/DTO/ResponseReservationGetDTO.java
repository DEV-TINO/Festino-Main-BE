package com.DevTino.festino_main.reservation.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseReservationGetDTO {
    UUID reservationId;
    Integer priorityNum;

}