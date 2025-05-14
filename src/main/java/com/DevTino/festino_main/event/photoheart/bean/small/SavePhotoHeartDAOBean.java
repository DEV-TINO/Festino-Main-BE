package com.DevTino.festino_main.event.photoheart.bean.small;

import com.DevTino.festino_main.event.photoheart.domain.entity.PhotoHeartDAO;
import com.DevTino.festino_main.event.photoheart.repository.PhotoHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePhotoHeartDAOBean {

    PhotoHeartRepositoryJPA photoHeartRepositoryJPA;

    @Autowired
    public SavePhotoHeartDAOBean(PhotoHeartRepositoryJPA photoHeartRepositoryJPA) {
        this.photoHeartRepositoryJPA = photoHeartRepositoryJPA;
    }

    public void exec(PhotoHeartDAO photoHeartDAO){
        photoHeartRepositoryJPA.save(photoHeartDAO);
    }
}
