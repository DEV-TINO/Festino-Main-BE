package com.DevTino.festino_main.notice.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import com.DevTino.festino_main.notice.repository.NoticeRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetRecentNoticeDAOBean {

    NoticeRepositoryJPA noticeRepositoryJPA;

    @Autowired
    public GetRecentNoticeDAOBean(NoticeRepositoryJPA noticeRepositoryJPA){
        this.noticeRepositoryJPA = noticeRepositoryJPA;
    }

    public NoticeDAO exec(){

        NoticeDAO dao = noticeRepositoryJPA.findTop1ByOrderByIsPinDescUpdateAtDesc();
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return dao;

    }
}
