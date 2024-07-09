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

        // 핀여부, 업로드 시간 순으로 내림차순 정렬하여 리스트로 가져오기
        List<NoticeDAO> noticeDAOList = getNoticesDAOBean.exec();

        return createNoticesDTOBean.exec(noticeDAOList);
    }
}
