package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotoGetDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CreatePhotosDTOBean {

    // 전체 조회
    public List<ResponsePhotoGetDTO> exec(List<PhotoDAO> photoDAOList) {
        return photoDAOList.stream()
                .map(photoDAO -> {
                    ResponsePhotoGetDTO responsePhotoGetDTO = new ResponsePhotoGetDTO();
                    responsePhotoGetDTO.setPhotoId(photoDAO.getPhotoId());
                    responsePhotoGetDTO.setMainUserName(photoDAO.getMainUserName());
                    responsePhotoGetDTO.setImageUrl(photoDAO.getImageUrl());
                    responsePhotoGetDTO.setCreateAt(photoDAO.getCreateAt());
                    responsePhotoGetDTO.setHeartCount(photoDAO.getHeartCount());
                    return responsePhotoGetDTO;
                })
                .toList();
    }

    // 유저의 사진 조회
    public List<ResponsePhotoGetDTO> exec(UUID mainUserId, List<PhotoDAO> photoDAOList) {
        return photoDAOList.stream()
                .filter(photoDAO -> photoDAO.getMainUserId().equals(mainUserId)) // 조건 추가
                .map(photoDAO -> {
                    ResponsePhotoGetDTO responsePhotoGetDTO = new ResponsePhotoGetDTO();
                    responsePhotoGetDTO.setPhotoId(photoDAO.getPhotoId());
                    responsePhotoGetDTO.setMainUserName(photoDAO.getMainUserName());
                    responsePhotoGetDTO.setImageUrl(photoDAO.getImageUrl());
                    responsePhotoGetDTO.setCreateAt(photoDAO.getCreateAt());
                    responsePhotoGetDTO.setHeartCount(photoDAO.getHeartCount());
                    return responsePhotoGetDTO;
                })
                .toList();
    }
}
