package com.DevTino.festino_main.event.photo.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponsePhotoGetDTO {
    private UUID photoId;
    private String mainUserName;
    private String imageUrl;
    private Integer heartCount;
    private LocalDateTime createAt;
}
