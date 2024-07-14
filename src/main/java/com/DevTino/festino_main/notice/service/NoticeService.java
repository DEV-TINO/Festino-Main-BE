package com.DevTino.festino_main.notice.service;

import com.DevTino.festino_main.notice.bean.GetNoticeBean;
import com.DevTino.festino_main.notice.bean.GetNoticesBean;
import com.DevTino.festino_main.notice.bean.GetRecentNoticeBean;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticeGetDTO;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticesGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoticeService {

    GetNoticeBean getNoticeBean;
    GetRecentNoticeBean getRecentNoticeBean;
    GetNoticesBean getNoticesBean;

    @Autowired
    public NoticeService(GetNoticeBean getNoticeBean, GetRecentNoticeBean getRecentNoticeBean, GetNoticesBean getNoticesBean){
        this.getNoticeBean = getNoticeBean;
        this.getRecentNoticeBean = getRecentNoticeBean;
        this.getNoticesBean = getNoticesBean;
    }

    // 특정 공지 조회
    public ResponseNoticeGetDTO getNotice(UUID noticeId){
        return getNoticeBean.exec(noticeId);
    }

    // 공지 전체 조회
    public List<ResponseNoticesGetDTO> getNotices(){
        return getNoticesBean.exec();
    }

    // 최근 공지 1개 조회 - pin
    public ResponseNoticeGetDTO getRecentNotice(){
        return getRecentNoticeBean.exec();
    }
}
