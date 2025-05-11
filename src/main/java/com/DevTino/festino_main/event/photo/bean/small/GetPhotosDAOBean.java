package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.event.photo.repository.PhotoRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPhotosDAOBean {

    PhotoRepositoryJPA photoRepositoryJPA;

    @Autowired
    public GetPhotosDAOBean(PhotoRepositoryJPA photoRepositoryJPA) {
        this.photoRepositoryJPA = photoRepositoryJPA;
    }

    // 사진 타입 별로 정렬해서 가져오기
    public List<PhotoDAO> exec(String type){
        // 타입 판단
        if (type.equals("new")) {
            return photoRepositoryJPA.findAllByOrderByCreateAtDesc();
        } else if (type.equals("heart")) {
            return photoRepositoryJPA.findAllByOrderByHeartCountDesc();
        } else {
            return null;
        }
    }
}
