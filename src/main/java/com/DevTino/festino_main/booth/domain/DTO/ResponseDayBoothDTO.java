package com.DevTino.festino_main.booth.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseDayBoothDTO {
    UUID boothId;
    Integer boothNum;
    String boothName;
    String adminCategory;
    String adminName;
    String openTime;
    String closeTime;
    String boothIntro;
    List<String> boothImage;
    String location;
    Boolean isOpen;
}