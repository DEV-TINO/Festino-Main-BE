package com.DevTino.festino_main.event.photoheart.bean.small;

import com.DevTino.festino_main.event.photoheart.domain.entity.PhotoHeartDAO;
import com.DevTino.festino_main.event.photoheart.repository.PhotoHeartRepositoryJPA;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetPhotoHeartDAOBean {

    PhotoHeartRepositoryJPA photoHeartRepositoryJPA;

    @Autowired
    public GetPhotoHeartDAOBean(PhotoHeartRepositoryJPA photoHeartRepositoryJPA) {
        this.photoHeartRepositoryJPA = photoHeartRepositoryJPA;
    }

    // photoId와 mainUserId를 통해 원하는 PhotoHeart 객체 찾기
    public PhotoHeartDAO exec(UUID photoId, UUID mainUserId) {

        PhotoHeartDAO dao = photoHeartRepositoryJPA.findByPhotoIdAndMainUserId(photoId, mainUserId);
        if (dao == null) throw new ServiceException(ExceptionEnum.ENTITY_NOT_FOUND);

        return dao;

    }
}
