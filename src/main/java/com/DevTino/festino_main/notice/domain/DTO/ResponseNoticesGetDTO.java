package com.DevTino.festino_main.notice.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseNoticesGetDTO {
    UUID noticeId;
    String title;
    String writerName;
    String imageUrl;
    String content;
    Boolean isPin;
}
