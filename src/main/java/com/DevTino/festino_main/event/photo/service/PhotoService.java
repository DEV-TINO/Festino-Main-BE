package com.DevTino.festino_main.event.photo.service;

import com.DevTino.festino_main.event.photo.bean.DeletePhotoBean;
import com.DevTino.festino_main.event.photo.bean.GetMainUserPhotosBean;
import com.DevTino.festino_main.event.photo.bean.GetPhotosBean;
import com.DevTino.festino_main.event.photo.bean.SavePhotoBean;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoDeleteDTO;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotoGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhotoService {

    GetPhotosBean getPhotosBean;
    GetMainUserPhotosBean getMainUserPhotosBean;
    SavePhotoBean savePhotoBean;
    DeletePhotoBean deletePhotoBean;

    @Autowired
    public PhotoService(GetPhotosBean getPhotosBean, GetMainUserPhotosBean getMainUserPhotosBean, SavePhotoBean savePhotoBean, DeletePhotoBean deletePhotoBean) {
        this.getPhotosBean = getPhotosBean;
        this.getMainUserPhotosBean = getMainUserPhotosBean;
        this.savePhotoBean = savePhotoBean;
        this.deletePhotoBean = deletePhotoBean;
    }

    // 사진 게시물 가져오기
    public List<ResponsePhotoGetDTO> getPhotos(String type) {
        return getPhotosBean.exec(type);
    }

    // 유저의 사진 게시물 가져오기
    public List<ResponsePhotoGetDTO> getMainUserPhotos(UUID mainUserId, String type) {
        return getMainUserPhotosBean.exec(mainUserId, type);
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
