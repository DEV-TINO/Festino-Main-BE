package com.DevTino.festino_main.image.bean.small;

import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SaveS3ImageDAOBean {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // S3 이미지 업로드
    public String exec(MultipartFile multipartFile) {
        try {
            // S3에 저장될 파일 이름
            String s3FileName = "festino/" + UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

            // S3에 저장될 파일 메타데이터
            ObjectMetadata objMeta = new ObjectMetadata();
            objMeta.setContentLength(multipartFile.getInputStream().available());

            // S3에 파일 저장
            amazonS3Client.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

            // S3에 저장된 파일 URL 반환
            return amazonS3Client.getUrl(bucket, s3FileName).toString();
        } catch (IOException e) {
            throw new ServiceException(ExceptionEnum.IMAGE_UPLOAD_FAIL);
        }
    }
}
