package com.DevTino.festino_main.notice.service;

import com.DevTino.festino_main.notice.bean.GetNoticeBean;
import com.DevTino.festino_main.notice.bean.GetRecentNoticeBean;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticeGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NoticeService {

    GetNoticeBean getNoticeBean;
    GetRecentNoticeBean getRecentNoticeBean;

    @Autowired
    public NoticeService(GetNoticeBean getNoticeBean, GetRecentNoticeBean getRecentNoticeBean){
        this.getNoticeBean = getNoticeBean;
        this.getRecentNoticeBean = getRecentNoticeBean;
    }

    // 특정 공지 조회
    public ResponseNoticeGetDTO getNotice(UUID noticeId){
        return getNoticeBean.exec(noticeId);
    }

    public ResponseNoticeGetDTO getRecentNotice(){
        return getRecentNoticeBean.exec();
    }
}
