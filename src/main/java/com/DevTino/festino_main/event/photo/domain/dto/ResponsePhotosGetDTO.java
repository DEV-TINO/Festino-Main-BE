package com.DevTino.festino_main.event.photo.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponsePhotosGetDTO {
    private Integer photoTotalCount;
    private List<ResponsePhotoGetDTO> photoList;
}
