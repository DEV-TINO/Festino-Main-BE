package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.event.photo.repository.PhotoRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeletePhotoDAOBean {

    PhotoRepositoryJPA photoRepositoryJPA;

    @Autowired
    public DeletePhotoDAOBean(PhotoRepositoryJPA photoRepositoryJPA) {
        this.photoRepositoryJPA = photoRepositoryJPA;
    }

    // 포토 DAO 삭제
    public void exec(PhotoDAO photoDAO) {
        photoRepositoryJPA.delete(photoDAO);
    }
}
