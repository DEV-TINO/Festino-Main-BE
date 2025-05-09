package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.event.photo.repository.PhotoRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePhotoDAOBean {

    PhotoRepositoryJPA photoRepositoryJPA;

    @Autowired
    public SavePhotoDAOBean(PhotoRepositoryJPA photoRepositoryJPA) {
        this.photoRepositoryJPA = photoRepositoryJPA;
    }

    public void exec(PhotoDAO photoDAO) {
        // 포토 DAO 저장
        photoRepositoryJPA.save(photoDAO);
    }
}
