package com.DevTino.festino_main.image.service;

import com.DevTino.festino_main.image.bean.SaveS3ImageBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class ImageService {

    private final SaveS3ImageBean saveS3ImageBean;

    // 이미지 저장
    public String saveImage(MultipartFile file) throws IOException {
        return saveS3ImageBean.exec(file);
    }
}

