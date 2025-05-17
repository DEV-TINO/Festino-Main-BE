package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.event.photo.repository.PhotoRepositoryJPA;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GetPhotosDAOBean {

    PhotoRepositoryJPA photoRepositoryJPA;

    @Autowired
    public GetPhotosDAOBean(PhotoRepositoryJPA photoRepositoryJPA) {
        this.photoRepositoryJPA = photoRepositoryJPA;
    }

    // 사진 타입 별로 정렬해서 가져오기
    public List<PhotoDAO> exec(String type){

        List<PhotoDAO> daoList = new ArrayList<>();

        // 타입 판단
        if (type.equals("new")) {
            daoList = photoRepositoryJPA.findAllByOrderByCreateAtDesc();
        } else if (type.equals("heart")) {
            daoList = photoRepositoryJPA.findAllByOrderByHeartCountDesc();
        } else {
            throw new ServiceException(ExceptionEnum.INVALID_INPUT_VALUE);
        }

        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;
    }

    // 사진 타입 별로 정렬해서 가져오기
    public List<PhotoDAO> exec(UUID mainUserId, String type){

        List<PhotoDAO> daoList = new ArrayList<>();

        // 타입 판단
        if (type.equals("new")) {
            daoList = photoRepositoryJPA.findAllByMainUserIdOrderByCreateAtDesc(mainUserId);
        } else if (type.equals("heart")) {
            daoList = photoRepositoryJPA.findAllByMainUserIdOrderByHeartCountDesc(mainUserId);
        } else {
            throw new ServiceException(ExceptionEnum.INVALID_INPUT_VALUE);
        }

        if (daoList.isEmpty()) throw new ServiceException(ExceptionEnum.EMPTY_LIST);

        return daoList;

    }
}
