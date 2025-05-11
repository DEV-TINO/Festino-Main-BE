package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotoGetDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatePhotosDTOBean {

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
}
