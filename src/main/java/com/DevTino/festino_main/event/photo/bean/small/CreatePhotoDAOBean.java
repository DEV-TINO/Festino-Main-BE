package com.DevTino.festino_main.event.photo.bean.small;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import com.DevTino.festino_main.event.photo.domain.entity.PhotoDAO;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreatePhotoDAOBean {

    public PhotoDAO exec(String mainUserName, RequestPhotoSaveDTO requestPhotoSaveDTO) {

        if (mainUserName.length() == 2) {
            mainUserName = mainUserName.charAt(0) + "*";
        } else if (mainUserName.length() >= 3) {
            StringBuilder sb = new StringBuilder(mainUserName);
            sb.setCharAt(mainUserName.length() - 2, '*');
            mainUserName = sb.toString();
        }

        // imageUrl 데이터가 없는 경우 예외 발생
        if (requestPhotoSaveDTO.getImageUrl() == null || requestPhotoSaveDTO.getImageUrl().isEmpty()) {
            throw new ServiceException(ExceptionEnum.INVALID_INPUT_VALUE);
        }

        return PhotoDAO.builder()
                .photoId(UUID.randomUUID())
                .mainUserId(requestPhotoSaveDTO.getMainUserId())
                .mainUserName(mainUserName)
                .imageUrl(requestPhotoSaveDTO.getImageUrl())
                .heartCount(0)
                .createAt(DateTimeUtils.nowZone())
                .build();
    }
}
