package com.DevTino.festino_main.event.photo.controller;

import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoDeleteDTO;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import com.DevTino.festino_main.event.photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/event/photo")
public class PhotoController {

    PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    // 사진 게시물 등록
    @PostMapping
    public ResponseEntity<Map<String, Object>> savePhoto(@RequestBody RequestPhotoSaveDTO requestPhotoSaveDTO) {

        UUID photoId = photoService.savePhoto(requestPhotoSaveDTO);

        boolean success = photoId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "사진 게시물 저장 성공" : "사진 게시물 저장 실패");
        requestMap.put("reviewId", photoId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 사진 게시물 삭제
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deletePhoto(@RequestBody RequestPhotoDeleteDTO requestPhotoDeleteDTO) {

        UUID photoId = photoService.deletePhoto(requestPhotoDeleteDTO);

        boolean success = photoId != null;

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", success);
        responseMap.put("message", success ? "사진 게시물 삭제 성공" : "사진 게시물 삭제 실패");

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }
}
