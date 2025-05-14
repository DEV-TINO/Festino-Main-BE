package com.DevTino.festino_main.event.photoheart.bean.small;

import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartSaveDTO;
import com.DevTino.festino_main.event.photoheart.domain.entity.PhotoHeartDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreatePhotoHeartDAOBean {

    public PhotoHeartDAO exec(RequestPhotoHeartSaveDTO requestPhotoHeartSaveDTO){
        return PhotoHeartDAO.builder()
                .photoHeartId(UUID.randomUUID())
                .photoId(requestPhotoHeartSaveDTO.getPhotoId())
                .mainUserId(requestPhotoHeartSaveDTO.getMainUserId())
                .createAt(LocalDateTime.now())
                .build();
    }
}
