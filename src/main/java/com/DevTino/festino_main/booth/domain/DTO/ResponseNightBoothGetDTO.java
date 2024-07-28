package com.DevTino.festino_main.booth.domain.DTO;

import com.DevTino.festino_main.booth.others.AccountInfoConverter;
import com.DevTino.festino_main.menu.domain.DTO.ResponseMenuGetDTO;
import jakarta.persistence.Convert;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class ResponseNightBoothGetDTO {
    UUID boothId;
    String boothName;
    String adminCategory;
    String adminName;
    String openTime;
    String closeTime;
    String boothIntro;
    List<String> boothImage;
    Boolean isOpen;
    Boolean isOrder;
    Boolean isReservation;
    Integer totalReservationNum;
    List<ResponseMenuGetDTO> menuList;
    Integer markerNum;
    String location;

    @Convert(converter = AccountInfoConverter.class)
    Map<String, String> accountInfo;
}