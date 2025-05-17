package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.event.photo.repository.PhotoRepositoryJPA;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetPhotoDAOBean {

    PhotoRepositoryJPA photoRepositoryJPA;

    @Autowired
    public GetPhotoDAOBean(PhotoRepositoryJPA photoRepositoryJPA) {
        this.photoRepositoryJPA = photoRepositoryJPA;
    }

    // 포토 DAO 가져오기
    public PhotoDAO exec(UUID photoId) {

        PhotoDAO dao = photoRepositoryJPA.findById(photoId).orElse(null);
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return dao;

    }
}
