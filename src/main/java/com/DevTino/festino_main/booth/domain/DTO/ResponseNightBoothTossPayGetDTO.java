package com.DevTino.festino_main.booth.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseNightBoothTossPayGetDTO {
    UUID boothId;
    Boolean isTossPay;
    String tossPay;
}
