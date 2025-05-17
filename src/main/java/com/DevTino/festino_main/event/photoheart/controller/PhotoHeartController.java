package com.DevTino.festino_main.event.photoheart.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartDeleteDTO;
import com.DevTino.festino_main.event.photoheart.domain.dto.RequestPhotoHeartSaveDTO;
import com.DevTino.festino_main.event.photoheart.service.PhotoHeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<Object>> savePhotoHeart(@RequestBody RequestPhotoHeartSaveDTO requestPhotoHeartSaveDTO) {
        UUID photoHeartId = photoHeartService.savePhotoHeart(requestPhotoHeartSaveDTO);

        ApiResponse<Object> response = new ApiResponse<>(true, "사진 좋아요 추가 성공", photoHeartId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 사진 좋아요 삭제
    @DeleteMapping
    public ResponseEntity<ApiResponse<Object>> deletePhotoHeart(@RequestBody RequestPhotoHeartDeleteDTO requestPhotoHeartDeleteDTO) {
        UUID photoHeartId = photoHeartService.deletePhotoHeart(requestPhotoHeartDeleteDTO);

        ApiResponse<Object> response = new ApiResponse<>(true, "사진 좋아요 삭제 성공", photoHeartId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
