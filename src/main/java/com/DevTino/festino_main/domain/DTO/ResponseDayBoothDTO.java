package com.DevTino.festino_main.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseDayBoothDTO {
    UUID boothId;
    String boothName;
    String adminCategory;
    String openTime;
    String closeTime;
    String boothIntro;
    String boothImage;
    String location;
    String descripteImage;
    String descripte;
    Boolean isOpen;
}