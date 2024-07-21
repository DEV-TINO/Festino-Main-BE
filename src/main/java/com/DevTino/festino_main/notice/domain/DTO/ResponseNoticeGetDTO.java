package com.DevTino.festino_main.notice.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseNoticeGetDTO {
    UUID noticeId;
    String title;
    LocalDateTime updateAt;
    List<String> imageUrl;
    String content;
    Boolean isPin;
}
