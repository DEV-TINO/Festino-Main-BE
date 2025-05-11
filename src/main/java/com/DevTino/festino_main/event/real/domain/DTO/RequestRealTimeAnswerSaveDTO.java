package com.DevTino.festino_main.event.real.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestRealTimeAnswerSaveDTO {
    UUID realTimeQuestionId;
    UUID mainUserId;

    String answer;
}
