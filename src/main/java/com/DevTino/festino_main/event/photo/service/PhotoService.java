package com.DevTino.festino_main.event.photo.service;

import com.DevTino.festino_main.event.photo.bean.DeletePhotoBean;
import com.DevTino.festino_main.event.photo.bean.SavePhotoBean;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoDeleteDTO;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhotoService {

    SavePhotoBean savePhotoBean;
    DeletePhotoBean deletePhotoBean;

    @Autowired
    public PhotoService(SavePhotoBean savePhotoBean, DeletePhotoBean deletePhotoBean) {
        this.savePhotoBean = savePhotoBean;
        this.deletePhotoBean = deletePhotoBean;
    }

    // 사진 게시물 등록
    public UUID savePhoto(RequestPhotoSaveDTO requestPhotoSaveDTO) {
        return savePhotoBean.exec(requestPhotoSaveDTO);
    }

    // 사진 게시물 삭제
    public UUID deletePhoto(RequestPhotoDeleteDTO requestPhotoDeleteDTO) {
        return deletePhotoBean.exec(requestPhotoDeleteDTO);
    }
}
