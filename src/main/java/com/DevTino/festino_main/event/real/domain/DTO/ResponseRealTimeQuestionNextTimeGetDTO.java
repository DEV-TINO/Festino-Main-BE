package com.DevTino.festino_main.event.real.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseRealTimeQuestionNextTimeGetDTO {
    LocalDateTime startTime;
    LocalDateTime endTime;
}
