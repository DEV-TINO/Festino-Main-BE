package com.DevTino.festino_main.notice.bean.small;

import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticesGetDTO;
import com.DevTino.festino_main.notice.domain.entity.NoticeDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateNoticesDTOBean {
    public ResponseNoticesGetDTO exec(NoticeDAO noticeDAO){
        return ResponseNoticesGetDTO.builder()
                .noticeId(noticeDAO.getNoticeId())
                .title(noticeDAO.getTitle())
                .updateAt(noticeDAO.getUpdateAt())
                .writerName(noticeDAO.getWriterName())
                .imageUrl(noticeDAO.getImageUrl())
                .content(noticeDAO.getContent())
                .isPin(noticeDAO.getIsPin())
                .build();
    }


    // 공지 전체 리스트로 반환
    public List<ResponseNoticesGetDTO> exec(List<NoticeDAO> noticeDAOList){

        List<ResponseNoticesGetDTO> responseNoticesGetDTOList = new ArrayList<>();

        // 공지 전체 리스트로 가져오기
        for (NoticeDAO noticeDAO : noticeDAOList){
            ResponseNoticesGetDTO responseNoticesGetDTO = exec(noticeDAO);

            responseNoticesGetDTOList.add(responseNoticesGetDTO);
        }

        return responseNoticesGetDTOList;
    }
}
