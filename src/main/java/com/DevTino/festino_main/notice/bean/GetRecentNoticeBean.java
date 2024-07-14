package com.DevTino.festino_main.notice.bean;

import com.DevTino.festino_main.notice.bean.small.CreateNoticeDTOBean;
import com.DevTino.festino_main.notice.bean.small.GetRecentNoticeDAOBean;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticeGetDTO;
import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetRecentNoticeBean {

    GetRecentNoticeDAOBean getRecentNoticeDAOBean;
    CreateNoticeDTOBean createNoticeDTOBean;

    @Autowired
    public GetRecentNoticeBean(GetRecentNoticeDAOBean getRecentNoticeDAOBean, CreateNoticeDTOBean createNoticeDTOBean) {
        this.getRecentNoticeDAOBean = getRecentNoticeDAOBean;
        this.createNoticeDTOBean = createNoticeDTOBean;
    }

    // 가장 최근 공지 조회 - pin
    public ResponseNoticeGetDTO exec(){

        // 공지에서 pin이 되어 있는 것을 찾고 최신순 top1으로 가져오기
        NoticeDAO noticeDAO = getRecentNoticeDAOBean.exec();

        // DTO로 반환
        return createNoticeDTOBean.exec(noticeDAO);
    }
}
