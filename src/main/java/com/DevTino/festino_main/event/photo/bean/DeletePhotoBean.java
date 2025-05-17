package com.DevTino.festino_main.event.photo.bean;

import com.DevTino.festino_main.event.photo.bean.small.DeletePhotoDAOBean;
import com.DevTino.festino_main.event.photo.bean.small.GetPhotoDAOBean;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoDeleteDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
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

        // user 일치여부 판단, 일치하지 않을 경우 예외 발생
        if (!requestPhotoDeleteDTO.getMainUserId().equals(photoDAO.getMainUserId())) throw new ServiceException(ExceptionEnum.MAIN_USERID_MISMATCH);

        // photo 삭제
        deletePhotoDAOBean.exec(photoDAO);

        // photoId 반환
        return requestPhotoDeleteDTO.getPhotoId();
    }
}
