package com.DevTino.festino_main.image.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<ApiResponse<Object>> saveImage(@RequestParam("file") MultipartFile file) {

        String imageUrl = imageService.saveImage(file);

        ApiResponse<Object> response = new ApiResponse<>(true, "이미지 저장 성공", imageUrl);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
