package com.DevTino.festino_main.notice.bean;

import com.DevTino.festino_main.notice.bean.small.CreateNoticeDTOBean;
import com.DevTino.festino_main.notice.bean.small.GetNoticeDAOBean;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticeDTO;
import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetNoticeBean {

    GetNoticeDAOBean getNoticeDAOBean;
    CreateNoticeDTOBean createNoticeDTOBean;

    @Autowired
    public GetNoticeBean(GetNoticeDAOBean getNoticeDAOBean, CreateNoticeDTOBean createNoticeDTOBean){
        this.getNoticeDAOBean = getNoticeDAOBean;
        this.createNoticeDTOBean = createNoticeDTOBean;
    }

    public ResponseNoticeDTO exec(UUID noticeId){
        NoticeDAO noticeDAO = getNoticeDAOBean.exec(noticeId);
        if (noticeDAO == null) return null;

        return createNoticeDTOBean.exec(noticeDAO);
    }
}
