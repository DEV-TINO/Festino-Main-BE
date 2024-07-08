package com.DevTino.festino_main.notice.bean.small;

import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import com.DevTino.festino_main.notice.repository.NoticeRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetNoticesDAOBean {

    NoticeRepositoryJPA noticeRepositoryJPA;

    @Autowired
    public GetNoticesDAOBean(NoticeRepositoryJPA noticeRepositoryJPA){
        this.noticeRepositoryJPA = noticeRepositoryJPA;
    }

    public List<NoticeDAO> exec() {
        return noticeRepositoryJPA.findAll();
    }
}
