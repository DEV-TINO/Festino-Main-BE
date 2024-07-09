package com.DevTino.festino_main.notice.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseNoticeGetDTO {
    UUID noticeId;
    String title;
    LocalDateTime updateAt;
    String writerName;
    String imageUrl;
    String content;
    Boolean isPin;
}
