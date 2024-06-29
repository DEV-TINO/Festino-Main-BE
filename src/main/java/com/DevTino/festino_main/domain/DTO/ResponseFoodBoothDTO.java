package com.DevTino.festino_main.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseFoodBoothDTO {
    UUID boothId;
    String boothName;
    String openTime;
    String closeTime;
    String boothIntro;
    String boothImage;
    String location;
    String descripteImage;
    Boolean isOpen;
}