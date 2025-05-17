package com.DevTino.festino_main.event.photoheart.bean;

import com.DevTino.festino_main.event.photo.bean.small.GetPhotoDAOBean;
import com.DevTino.festino_main.event.photo.bean.small.SavePhotoDAOBean;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.event.photoheart.bean.small.CreatePhotoHeartDAOBean;
import com.DevTino.festino_main.event.photoheart.bean.small.DeletePhotoHeartDAOBean;
import com.DevTino.festino_main.event.photoheart.bean.small.GetPhotoHeartDAOBean;
import com.DevTino.festino_main.event.photoheart.bean.small.SavePhotoHeartDAOBean;
import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartDeleteDTO;
import com.DevTino.festino_main.event.photoheart.domain.entity.PhotoHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeletePhotoHeartBean {

    GetPhotoHeartDAOBean getPhotoHeartDAOBean;
    GetPhotoDAOBean getPhotoDAOBean;
    DeletePhotoHeartDAOBean deletePhotoHeartDAOBean;
    SavePhotoDAOBean savePhotoDAOBean;

    @Autowired
    public DeletePhotoHeartBean(GetPhotoHeartDAOBean getPhotoHeartDAOBean, GetPhotoDAOBean getPhotoDAOBean, DeletePhotoHeartDAOBean deletePhotoHeartDAOBean, SavePhotoDAOBean savePhotoDAOBean) {
        this.getPhotoHeartDAOBean = getPhotoHeartDAOBean;
        this.getPhotoDAOBean = getPhotoDAOBean;
        this.deletePhotoHeartDAOBean = deletePhotoHeartDAOBean;
        this.savePhotoDAOBean = savePhotoDAOBean;
    }

    // 사진 게시물 좋아요 삭제
    public UUID exec(RequestPhotoHeartDeleteDTO requestPhotoHeartDeleteDTO){

        // photoId와 mainUserId로 해당 PhotoHeart(DAO) 찾기
        PhotoHeartDAO photoHeartDAO = getPhotoHeartDAOBean.exec(requestPhotoHeartDeleteDTO.getPhotoId(), requestPhotoHeartDeleteDTO.getMainUserId());

        // commentId를 통해 원하는 comment 객체 찾기
        PhotoDAO photoDAO = getPhotoDAOBean.exec(photoHeartDAO.getPhotoId());

        // 찾은 photoDAO 객체 좋아요 삭제
        photoDAO.setHeartCount(photoDAO.getHeartCount() - 1);

        // 사진 게시물 좋아요 저장
        deletePhotoHeartDAOBean.exec(photoHeartDAO);

        // 사진 게시물 저장
        savePhotoDAOBean.exec(photoDAO);

        // 키값을 반환
        return photoHeartDAO.getPhotoHeartId();
    }
}
