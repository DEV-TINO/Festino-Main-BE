package com.DevTino.festino_main.event.photoheart.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestPhotoHeartDeleteDTO {
    private UUID photoId;
    private UUID mainUserId;
}
