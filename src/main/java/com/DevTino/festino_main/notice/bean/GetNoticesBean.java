package com.DevTino.festino_main.notice.bean;

import com.DevTino.festino_main.notice.bean.small.CreateNoticesDTOBean;
import com.DevTino.festino_main.notice.bean.small.GetNoticesDAOBean;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticesGetDTO;
import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetNoticesBean {

    GetNoticesDAOBean getNoticesDAOBean;
    CreateNoticesDTOBean createNoticesDTOBean;

    @Autowired
    public GetNoticesBean(GetNoticesDAOBean getNoticesDAOBean, CreateNoticesDTOBean createNoticesDTOBean){
        this.getNoticesDAOBean = getNoticesDAOBean;
        this.createNoticesDTOBean = createNoticesDTOBean;
    }

    // 공지 전체 리스트 가져온 다음 반환
    public List<ResponseNoticesGetDTO> exec(){

        List<NoticeDAO> noticeDAOList = getNoticesDAOBean.exec();

        return createNoticesDTOBean.exec(noticeDAOList);
    }
}
