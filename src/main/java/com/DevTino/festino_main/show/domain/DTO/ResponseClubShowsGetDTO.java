package com.DevTino.festino_main.show.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseClubShowsGetDTO {
    UUID clubId;
    String clubName;
    String showData;
    String showStartTime;
    String showEndTime;
    String clubImage;
    String clubDescription;
    Boolean isShowing;
}
