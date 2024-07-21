package com.DevTino.festino_main.notice.bean.small;

import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticeGetDTO;
import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateNoticeDTOBean {

    // DTO 값 넣기
    public ResponseNoticeGetDTO exec(NoticeDAO noticeDAO){
        return ResponseNoticeGetDTO.builder()
                .noticeId(noticeDAO.getNoticeId())
                .title(noticeDAO.getTitle())
                .updateAt(noticeDAO.getUpdateAt())
                .imageUrl(noticeDAO.getImageUrl())
                .content(noticeDAO.getContent())
                .isPin(noticeDAO.getIsPin())
                .build();
    }
}
