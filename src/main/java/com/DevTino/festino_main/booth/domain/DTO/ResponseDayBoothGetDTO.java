package com.DevTino.festino_main.booth.domain.DTO;

import com.DevTino.festino_main.menu.domain.DTO.ResponseMenuGetDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseDayBoothGetDTO {
    UUID boothId;
    String boothName;
    String adminCategory;
    String adminName;
    String openTime;
    String closeTime;
    String boothIntro;
    List<String> boothImage;
    List<ResponseMenuGetDTO> menuList;
    Integer markerNum;
    String location;
    Boolean isOpen;
    String instagram;
}