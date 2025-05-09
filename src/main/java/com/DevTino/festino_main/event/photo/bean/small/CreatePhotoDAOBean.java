package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreatePhotoDAOBean {

    public PhotoDAO exec(String mainUserName, RequestPhotoSaveDTO requestPhotoSaveDTO) {

        return PhotoDAO.builder()
                .photoId(UUID.randomUUID())
                .mainUserId(requestPhotoSaveDTO.getMainUserId())
                .mainUserName(mainUserName)
                .imageUrl(requestPhotoSaveDTO.getImageUrl())
                .heartCount(0)
                .createAt(LocalDateTime.now())
                .build();
    }
}
