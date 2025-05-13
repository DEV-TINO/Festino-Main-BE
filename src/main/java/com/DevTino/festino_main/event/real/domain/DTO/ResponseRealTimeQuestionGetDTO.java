package com.DevTino.festino_main.event.real.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseRealTimeQuestionGetDTO {
    UUID realTimeQuestionId;
    String question;
}
