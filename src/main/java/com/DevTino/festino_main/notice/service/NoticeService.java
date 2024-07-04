package com.DevTino.festino_main.notice.service;

import com.DevTino.festino_main.notice.bean.GetNoticeBean;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticeGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NoticeService {

    GetNoticeBean getNoticeBean;

    @Autowired
    public NoticeService(GetNoticeBean getNoticeBean){
        this.getNoticeBean = getNoticeBean;
    }

    // 특정 공지 조회
    public ResponseNoticeGetDTO getNotice(UUID noticeId){
        return getNoticeBean.exec(noticeId);
    }
}
