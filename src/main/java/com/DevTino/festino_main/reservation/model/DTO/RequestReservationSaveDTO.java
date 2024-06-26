package com.DevTino.festino_main.reservation.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestReservationSaveDTO {
    String userName;
    String phoneNum;
    Integer personCount;
    UUID boothId;
}