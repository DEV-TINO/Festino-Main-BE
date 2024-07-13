package com.DevTino.festino_main.reservation.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestReservationSaveDTO {
    String userName;
    String phoneNum;
    Integer personCount;
    UUID boothId;
}