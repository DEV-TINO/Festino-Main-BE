package com.DevTino.festino_main.notice.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

    // 공지 전체 리스트 가져오기
    public List<NoticeDAO> exec() {

        List<NoticeDAO> daoList = noticeRepositoryJPA.findByOrderByIsPinDescUpdateAtDesc();
        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;

    }
}
