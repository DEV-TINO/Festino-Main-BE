package com.DevTino.festino_main.notice.bean.small;

import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticeDTO;
import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateNoticeDTOBean {

    public ResponseNoticeDTO exec(NoticeDAO noticeDAO){
        return ResponseNoticeDTO.builder()
                .noticeId(noticeDAO.getNoticeId())
                .title(noticeDAO.getTitle())
                .writerName(noticeDAO.getWriterName())
                .imageUrl(noticeDAO.getImageUrl())
                .content(noticeDAO.getContent())
                .isPin(noticeDAO.getIsPin())
                .build();
    }
}
