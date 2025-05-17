package com.DevTino.festino_main.image.controller;

import com.DevTino.festino_main.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/image")
public class ImageController {

    ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // 이미지 저장
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> saveImage(@RequestParam("file") MultipartFile file) throws IOException {

        String imageUrl = imageService.saveImage(file);

        // 메뉴 저장 성공 여부
        boolean success = imageUrl != null;

        // Map을 통해 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "이미지 저장 성공" : "이미지 저장 실패");
        requestMap.put("imageUrl", imageUrl);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
