package com.DevTino.festino_main.event.photoheart.bean.small;

import com.DevTino.festino_main.event.photoheart.domain.entity.PhotoHeartDAO;
import com.DevTino.festino_main.event.photoheart.repository.PhotoHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePhotoHeartDAOBean {

    PhotoHeartRepositoryJPA photoHeartRepositoryJPA;

    @Autowired
    public DeletePhotoHeartDAOBean(PhotoHeartRepositoryJPA photoHeartRepositoryJPA) {
        this.photoHeartRepositoryJPA = photoHeartRepositoryJPA;
    }

    // 사진 게시물 좋아요 삭제
    public void exec(PhotoHeartDAO photoHeartDAO) {
        photoHeartRepositoryJPA.delete(photoHeartDAO);
    }
}
