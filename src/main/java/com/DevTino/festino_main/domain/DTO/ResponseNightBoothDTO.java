package com.DevTino.festino_main.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseNightBoothDTO {
    UUID boothId;
    String boothName;
    String adminCategory;
    String adminName;
    String openTime;
    String closeTime;
    String boothIntro;
    String boothImage;
    String location;
    Boolean isOpen;
    Boolean isOrder;
    Boolean isReservation;
    Integer totalReservationNum;
}