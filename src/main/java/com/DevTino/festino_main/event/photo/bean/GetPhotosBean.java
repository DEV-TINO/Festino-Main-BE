package com.DevTino.festino_main.event.photo.bean;

import com.DevTino.festino_main.event.photo.bean.small.CreatePhotosDTOBean;
import com.DevTino.festino_main.event.photo.bean.small.GetPhotosDAOBean;
import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotosGetDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetPhotosBean {

    GetPhotosDAOBean getPhotosDAOBean;
    CreatePhotosDTOBean createPhotosDTOBean;

    @Autowired
    public GetPhotosBean(GetPhotosDAOBean getPhotosDAOBean, CreatePhotosDTOBean createPhotosDTOBean) {
        this.getPhotosDAOBean = getPhotosDAOBean;
        this.createPhotosDTOBean = createPhotosDTOBean;
    }

    public ResponsePhotosGetDTO exec(UUID mainUserId, String type){

        // 사진 타입 별로 정렬해서 가져오기
        List<PhotoDAO> photoDAOList = getPhotosDAOBean.exec(type);

        // DAO -> DTO 변환
        return createPhotosDTOBean.exec(mainUserId, photoDAOList);

    }
}
