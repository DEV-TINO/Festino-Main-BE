package com.DevTino.festino_main.event.photo.bean;

import com.DevTino.festino_main.event.photo.bean.small.CreatePhotoDAOBean;
import com.DevTino.festino_main.event.photo.bean.small.SavePhotoDAOBean;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.user.bean.small.GetMainUserDAOBean;
import com.DevTino.festino_main.user.domain.entity.MainUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SavePhotoBean {

    GetMainUserDAOBean getMainUserDAOBean;
    CreatePhotoDAOBean createPhotoDAOBean;
    SavePhotoDAOBean savePhotoDAOBean;

    @Autowired
    public SavePhotoBean(GetMainUserDAOBean getMainUserDAOBean, CreatePhotoDAOBean createPhotoDAOBean, SavePhotoDAOBean savePhotoDAOBean){
        this.getMainUserDAOBean = getMainUserDAOBean;
        this.createPhotoDAOBean = createPhotoDAOBean;
        this.savePhotoDAOBean = savePhotoDAOBean;
    }

    public UUID exec(RequestPhotoSaveDTO requestPhotoSaveDTO){

        // 유저 정보 가져오기
        MainUserDAO mainUserDAO = getMainUserDAOBean.exec(requestPhotoSaveDTO.getMainUserId());

        // 포토 DAO 생성
        PhotoDAO photoDAO = createPhotoDAOBean.exec(mainUserDAO.getMainUserName(), requestPhotoSaveDTO);

        // DAO 저장
        savePhotoDAOBean.exec(photoDAO);

        // reviewID 반환
        return photoDAO.getPhotoId();
    }
}
