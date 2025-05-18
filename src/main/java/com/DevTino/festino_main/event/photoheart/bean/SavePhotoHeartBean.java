package com.DevTino.festino_main.event.photoheart.bean;

import com.DevTino.festino_main.event.photo.bean.small.GetPhotoDAOBean;
import com.DevTino.festino_main.event.photo.bean.small.SavePhotoDAOBean;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.event.photoheart.bean.small.CheckPhotoHeartDAOBean;
import com.DevTino.festino_main.event.photoheart.bean.small.CreatePhotoHeartDAOBean;
import com.DevTino.festino_main.event.photoheart.bean.small.SavePhotoHeartDAOBean;
import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartSaveDTO;
import com.DevTino.festino_main.event.photoheart.domain.entity.PhotoHeartDAO;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SavePhotoHeartBean {

    CheckPhotoHeartDAOBean checkPhotoHeartDAOBean;
    CreatePhotoHeartDAOBean createPhotoHeartDAOBean;
    GetPhotoDAOBean getPhotoDAOBean;
    SavePhotoHeartDAOBean savePhotoHeartDAOBean;
    SavePhotoDAOBean savePhotoDAOBean;

    @Autowired
    public SavePhotoHeartBean(CheckPhotoHeartDAOBean checkPhotoHeartDAOBean, CreatePhotoHeartDAOBean createPhotoHeartDAOBean, GetPhotoDAOBean getPhotoDAOBean, SavePhotoHeartDAOBean savePhotoHeartDAOBean, SavePhotoDAOBean savePhotoDAOBean) {
        this.checkPhotoHeartDAOBean = checkPhotoHeartDAOBean;
        this.createPhotoHeartDAOBean = createPhotoHeartDAOBean;
        this.getPhotoDAOBean = getPhotoDAOBean;
        this.savePhotoHeartDAOBean = savePhotoHeartDAOBean;
        this.savePhotoDAOBean = savePhotoDAOBean;
    }

    // 사진 게시물 좋아요 저장
    public UUID exec(RequestPhotoHeartSaveDTO requestPhotoHeartSaveDTO) {

        // 이미 좋아요 되어 있으면 예외 발생
        if (checkPhotoHeartDAOBean.exec(requestPhotoHeartSaveDTO.getPhotoId(), requestPhotoHeartSaveDTO.getMainUserId()))
            throw new ServiceException(ExceptionEnum.ALREADY_PROCESSED);

        // 사진 게시물 좋아요 객체 생성
        PhotoHeartDAO photoHeartDAO = createPhotoHeartDAOBean.exec(requestPhotoHeartSaveDTO);

        // photoId 통해 원하는 photoDAO 객체 찾기
        PhotoDAO photoDAO = getPhotoDAOBean.exec(requestPhotoHeartSaveDTO.getPhotoId());

        //찾은 photoDAO 객체 좋아요 추가
        photoDAO.setHeartCount(photoDAO.getHeartCount() + 1);

        // 사진 게시물  저장
        savePhotoHeartDAOBean.exec(photoHeartDAO);

        // 사진 게시물 저장
        savePhotoDAOBean.exec(photoDAO);

        // 키값을 반환
        return photoHeartDAO.getPhotoHeartId();
    }
}
