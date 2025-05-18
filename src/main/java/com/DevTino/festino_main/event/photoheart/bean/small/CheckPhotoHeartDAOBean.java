package com.DevTino.festino_main.event.photoheart.bean.small;

import com.DevTino.festino_main.event.photoheart.repository.PhotoHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CheckPhotoHeartDAOBean {

    PhotoHeartRepositoryJPA photoHeartRepositoryJPA;

    @Autowired
    public CheckPhotoHeartDAOBean(PhotoHeartRepositoryJPA photoHeartRepositoryJPA) {
        this.photoHeartRepositoryJPA = photoHeartRepositoryJPA;
    }



    // photoId와 mainUserId를 통해 원하는 PhotoHeart 객체 찾기
    public boolean exec(UUID photoId, UUID mainUserId) {

        return photoHeartRepositoryJPA.existsByPhotoIdAndMainUserId(photoId, mainUserId);

    }
}