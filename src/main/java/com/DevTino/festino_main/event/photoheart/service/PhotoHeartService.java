package com.DevTino.festino_main.event.photoheart.service;

import com.DevTino.festino_main.event.photoheart.bean.SavePhotoHeartBean;
import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhotoHeartService {

    SavePhotoHeartBean savePhotoHeartBean;

    @Autowired
    public PhotoHeartService(SavePhotoHeartBean savePhotoHeartBean) {
        this.savePhotoHeartBean = savePhotoHeartBean;
    }

    // 사진 게시물 좋아요 저장
    public UUID savePhotoHeart(RequestPhotoHeartSaveDTO requestPhotoHeartSaveDTO) {
        return savePhotoHeartBean.exec(requestPhotoHeartSaveDTO);
    }
}
