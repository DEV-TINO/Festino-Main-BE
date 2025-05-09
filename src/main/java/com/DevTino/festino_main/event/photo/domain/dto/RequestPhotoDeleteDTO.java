package com.DevTino.festino_main.event.photo.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestPhotoDeleteDTO {
    private UUID photoId;
    private UUID mainUserId;
}
