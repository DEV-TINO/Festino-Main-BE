package com.DevTino.festino_main.event.photoheart.controller;

import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartDeleteDTO;
import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartSaveDTO;
import com.DevTino.festino_main.event.photoheart.service.PhotoHeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/main/event/photo/heart")
public class PhotoHeartController {

    PhotoHeartService photoHeartService;

    @Autowired
    public PhotoHeartController(PhotoHeartService photoHeartService) {
        this.photoHeartService = photoHeartService;
    }

    // 사진 좋아요 추가
    @PostMapping
    public ResponseEntity<Map<String, Object>> savePhotoHeart(@RequestBody RequestPhotoHeartSaveDTO requestPhotoHeartSaveDTO) {
        UUID photoHeartId = photoHeartService.savePhotoHeart(requestPhotoHeartSaveDTO);

        boolean success = photoHeartId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "사진 게시물 좋아요 저장 성공" : "사진 게시물 좋아요 저장 실패");
        requestMap.put("photoHeartId", photoHeartId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 사진 좋아요 삭제
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deletePhotoHeart(@RequestBody RequestPhotoHeartDeleteDTO requestPhotoHeartDeleteDTO) {
        UUID photoHeartId = photoHeartService.deletePhotoHeart(requestPhotoHeartDeleteDTO);

        boolean success = photoHeartId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "사진 게시물 좋아요 삭제 성공" : "사진 게시물 좋아요 삭제 실패");
        requestMap.put("photoHeartId", photoHeartId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
