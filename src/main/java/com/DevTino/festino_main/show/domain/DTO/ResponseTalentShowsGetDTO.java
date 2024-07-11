package com.DevTino.festino_main.show.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class ResponseTalentShowsGetDTO {
    UUID talentId;
    String talentName;
    String showDate;
    String showStartTime;
    String showEndTime;
    String talentImage;
    List<Map<String, Object>> msuicList;
    Boolean isShowing;
}
