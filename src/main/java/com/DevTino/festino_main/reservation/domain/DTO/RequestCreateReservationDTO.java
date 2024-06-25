package com.DevTino.festino_main.reservation.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestCreateReservationDTO {
    String userName;
    String phoneNum;
    Integer personCount;
    UUID boothId;
}