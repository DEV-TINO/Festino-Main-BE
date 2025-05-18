package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotoGetDTO;
import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotosGetDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.event.photoheart.bean.small.CheckPhotoHeartDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CreatePhotosDTOBean {

    CheckPhotoHeartDAOBean checkPhotoHeartDAOBean;

    @Autowired
    public CreatePhotosDTOBean(CheckPhotoHeartDAOBean checkPhotoHeartDAOBean) {
        this.checkPhotoHeartDAOBean = checkPhotoHeartDAOBean;
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
                    responsePhotoGetDTO.setHeart(checkPhotoHeartDAOBean.exec(photoDAO.getPhotoId(), mainUserId));

                    return responsePhotoGetDTO;
                })
                .toList();

        return ResponsePhotosGetDTO.builder()
                .photoTotalCount(photoDAOList.size())
                .photoList(responsePhotoGetDTOList)
                .build();
    }
}