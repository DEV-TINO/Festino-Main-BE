package com.DevTino.festino_main.event.photo.service;

import com.DevTino.festino_main.event.photo.bean.SavePhotoBean;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhotoService {

    SavePhotoBean savePhotoBean;

    @Autowired
    public PhotoService(SavePhotoBean savePhotoBean) {
        this.savePhotoBean = savePhotoBean;
    }

    // 사진 게시물 등록
    public UUID savePhoto(RequestPhotoSaveDTO requestPhotoSaveDTO) {
        return savePhotoBean.exec(requestPhotoSaveDTO);
    }

}
