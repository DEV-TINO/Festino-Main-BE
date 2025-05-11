package com.DevTino.festino_main.event.photo.bean;

import com.DevTino.festino_main.event.photo.bean.small.CreatePhotoDAOBean;
import com.DevTino.festino_main.event.photo.bean.small.SavePhotoDAOBean;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SavePhotoBean {

    CreatePhotoDAOBean createPhotoDAOBean;
    SavePhotoDAOBean savePhotoDAOBean;

    @Autowired
    public SavePhotoBean(CreatePhotoDAOBean createPhotoDAOBean, SavePhotoDAOBean savePhotoDAOBean){
        this.createPhotoDAOBean = createPhotoDAOBean;
        this.savePhotoDAOBean = savePhotoDAOBean;
    }

    public UUID exec(RequestPhotoSaveDTO requestPhotoSaveDTO){

        // 유저 정보 가져오기

        // 포토 DAO 생성
        PhotoDAO photoDAO = createPhotoDAOBean.exec("test", requestPhotoSaveDTO);
        if (photoDAO == null) return null;

        // DAO 저장
        savePhotoDAOBean.exec(photoDAO);

        // reviewID 반환
        return photoDAO.getPhotoId();
    }
}
