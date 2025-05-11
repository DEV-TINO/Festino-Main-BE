package com.DevTino.festino_main.event.real.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestRealTimeQuestionSaveDTO {
    UUID mainUserId;
    String question;
}
