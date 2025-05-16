package com.DevTino.festino_main.event.photoheart.service;

import com.DevTino.festino_main.event.photoheart.bean.DeletePhotoHeartBean;
import com.DevTino.festino_main.event.photoheart.bean.SavePhotoHeartBean;
import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartDeleteDTO;
import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhotoHeartService {

    SavePhotoHeartBean savePhotoHeartBean;
    DeletePhotoHeartBean deletePhotoHeartBean;

    @Autowired
    public PhotoHeartService(SavePhotoHeartBean savePhotoHeartBean, DeletePhotoHeartBean deletePhotoHeartBean) {
        this.savePhotoHeartBean = savePhotoHeartBean;
        this.deletePhotoHeartBean = deletePhotoHeartBean;
    }

    // 사진 게시물 좋아요 저장
    public UUID savePhotoHeart(RequestPhotoHeartSaveDTO requestPhotoHeartSaveDTO) {
        return savePhotoHeartBean.exec(requestPhotoHeartSaveDTO);
    }

    // 사진 게시물 좋아요 삭제
    public UUID deletePhotoHeart(RequestPhotoHeartDeleteDTO requestPhotoHeartDeleteDTO) {
        return deletePhotoHeartBean.exec(requestPhotoHeartDeleteDTO);
    }
}
