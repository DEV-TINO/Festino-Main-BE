package com.DevTino.festino_main.image.bean;

import com.DevTino.festino_main.image.bean.small.SaveS3ImageDAOBean;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class SaveS3ImageBean {

    SaveS3ImageDAOBean saveS3ImageDAOBean;

    @Autowired
    public SaveS3ImageBean(SaveS3ImageDAOBean saveS3ImageDAOBean) {
        this.saveS3ImageDAOBean = saveS3ImageDAOBean;
    }

    // 이미지 저장
    public String exec(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            return null;
        }

        // S3 이미지 저장
        return saveS3ImageDAOBean.exec(file);

    }
}
