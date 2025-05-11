package com.DevTino.festino_main.event.photo.bean;

import com.DevTino.festino_main.event.photo.bean.small.DeletePhotoDAOBean;
import com.DevTino.festino_main.event.photo.bean.small.GetPhotoDAOBean;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoDeleteDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeletePhotoBean {

    GetPhotoDAOBean getPhotoDAOBean;
    DeletePhotoDAOBean deletePhotoDAOBean;

    @Autowired
    public DeletePhotoBean(GetPhotoDAOBean getPhotoDAOBean, DeletePhotoDAOBean deletePhotoDAOBean){
        this.getPhotoDAOBean = getPhotoDAOBean;
        this.deletePhotoDAOBean = deletePhotoDAOBean;
    }

    // 사진 게시물 삭제
    public UUID exec(RequestPhotoDeleteDTO requestPhotoDeleteDTO){

        // photo 존재여부 판단
        PhotoDAO photoDAO = getPhotoDAOBean.exec(requestPhotoDeleteDTO.getPhotoId());
        if (photoDAO == null) return null; // 존재하지 않는 photoId

        // user 일치여부 판단
        if (!requestPhotoDeleteDTO.getMainUserId().equals(photoDAO.getMainUserId())) return null; // userId 불일치

        // photo 삭제
        deletePhotoDAOBean.exec(photoDAO);

        // photoId 반환
        return requestPhotoDeleteDTO.getPhotoId();
    }
}
