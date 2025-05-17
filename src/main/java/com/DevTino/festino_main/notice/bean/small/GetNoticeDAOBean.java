package com.DevTino.festino_main.notice.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

        NoticeDAO dao = noticeRepositoryJPA.findById(noticeId).orElse(null);
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return dao;

    }
}
