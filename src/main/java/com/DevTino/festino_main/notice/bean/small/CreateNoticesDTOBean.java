package com.DevTino.festino_main.notice.bean.small;

import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticesGetDTO;
import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import org.apache.coyote.Response;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateNoticesDTOBean {
    public ResponseNoticesGetDTO exec(NoticeDAO noticeDAO){
        return ResponseNoticesGetDTO.builder()
                .noticeId(noticeDAO.getNoticeId())
                .title(noticeDAO.getTitle())
                .writerName(noticeDAO.getWriterName())
                .imageUrl(noticeDAO.getImageUrl())
                .content(noticeDAO.getContent())
                .isPin(noticeDAO.getIsPin())
                .build();
    }

    public List<ResponseNoticesGetDTO> exec(List<NoticeDAO> noticeDAOList){

        List<ResponseNoticesGetDTO> responseNoticesGetDTOList = new ArrayList<>();

        for (NoticeDAO noticeDAO : noticeDAOList){
            ResponseNoticesGetDTO responseNoticesGetDTO = exec(noticeDAO);

            responseNoticesGetDTOList.add(responseNoticesGetDTO);
        }

        return responseNoticesGetDTOList;
    }
}
