package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotoGetDTO;
import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotosGetDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.event.photoheart.bean.small.GetPhotoHeartDAOBean;
import com.DevTino.festino_main.event.photoheart.domain.entity.PhotoHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CreatePhotosDTOBean {

    GetPhotoHeartDAOBean getPhotoHeartDAOBean;

    @Autowired
    public CreatePhotosDTOBean(GetPhotoHeartDAOBean getPhotoHeartDAOBean) {
        this.getPhotoHeartDAOBean = getPhotoHeartDAOBean;
    }

    // 전체 조회
    public ResponsePhotosGetDTO exec(UUID mainUserId, List<PhotoDAO> photoDAOList) {

        List<ResponsePhotoGetDTO> responsePhotoGetDTOList = photoDAOList.stream()
                .map(photoDAO -> {
                    ResponsePhotoGetDTO responsePhotoGetDTO = new ResponsePhotoGetDTO();
                    responsePhotoGetDTO.setPhotoId(photoDAO.getPhotoId());
                    responsePhotoGetDTO.setMainUserName(photoDAO.getMainUserName());
                    responsePhotoGetDTO.setImageUrl(photoDAO.getImageUrl());
                    responsePhotoGetDTO.setCreateAt(photoDAO.getCreateAt());
                    responsePhotoGetDTO.setHeartCount(photoDAO.getHeartCount());

                    // 좋아요 여부 확인
                    PhotoHeartDAO photoHeartDAO = getPhotoHeartDAOBean.exec(photoDAO.getPhotoId(), mainUserId);
                    responsePhotoGetDTO.setHeart(photoHeartDAO != null);

                    return responsePhotoGetDTO;
                })
                .toList();

        return ResponsePhotosGetDTO.builder()
                .photoTotalCount(photoDAOList.size())
                .photoList(responsePhotoGetDTOList)
                .build();
    }
}
