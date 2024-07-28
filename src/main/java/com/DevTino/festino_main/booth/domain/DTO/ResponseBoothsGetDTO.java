package com.DevTino.festino_main.booth.domain.DTO;

import com.DevTino.festino_main.booth.others.AccountInfoConverter;
import jakarta.persistence.Convert;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class ResponseBoothsGetDTO {
    UUID boothId;
    String boothName;
    String adminCategory;
    String adminName;
    String openTime;
    String closeTime;
    String boothIntro;
    String boothImage;
    Integer markerNum;
    String location;
    Boolean isOpen;

    @Convert(converter = AccountInfoConverter.class)
    Map<String, String> accountInfo;
}
