package com.DevTino.festino_main.event.photo.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestPhotoSaveDTO {
    private UUID mainUserId;
    private String imageUrl;
}
