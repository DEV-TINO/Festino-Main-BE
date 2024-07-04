package com.DevTino.festino_main.notice.bean.small;

import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import com.DevTino.festino_main.notice.repository.NoticeRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetNoticeDAOBean {

    NoticeRepositoryJPA noticeRepositoryJPA;

    @Autowired
    public GetNoticeDAOBean(NoticeRepositoryJPA noticeRepositoryJPA){
        this.noticeRepositoryJPA = noticeRepositoryJPA;
    }

    // DAO 가져오기
    public NoticeDAO exec(UUID noticeId){
        return noticeRepositoryJPA.findById(noticeId).orElse(null);
    }
}
