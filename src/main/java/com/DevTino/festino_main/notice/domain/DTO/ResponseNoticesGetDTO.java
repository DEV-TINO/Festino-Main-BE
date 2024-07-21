package com.DevTino.festino_main.notice.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseNoticesGetDTO {
    UUID noticeId;
    String title;
    String writerName;
    LocalDateTime updateAt;
    String content;
    Boolean isPin;
}
