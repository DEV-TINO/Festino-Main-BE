package com.DevTino.festino_main.event.photo.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoDeleteDTO;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotosGetDTO;
import com.DevTino.festino_main.event.photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 사진 게시물 전체 조회
    @GetMapping("all/{type}/user/{mainUserId}") // type : new, heart
    public ResponseEntity<ApiResponse<Object>> getPhotos(@PathVariable("mainUserId") UUID mainUserId, @PathVariable("type") String type) {

        // 사진 게시물 전체 조회 service 실행
        ResponsePhotosGetDTO responsePhotosGetDTO = photoService.getPhotos(mainUserId, type);

        ApiResponse<Object> response = new ApiResponse<>(true, "사진 게시물 전체 조회 성공", responsePhotosGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 유저 사진 게시물 전체 조회
    @GetMapping("my/{type}/user/{mainUserId}")
    public ResponseEntity<ApiResponse<Object>> getUserPhotos(@PathVariable("type") String type, @PathVariable("mainUserId") UUID mainUserId) {

        // 사진 게시물 전체 조회 service 실행
        ResponsePhotosGetDTO responsePhotosGetDTO = photoService.getMainUserPhotos(mainUserId, type);

        ApiResponse<Object> response = new ApiResponse<>(true, "유저 사진 게시물 전체 조회 성공", responsePhotosGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 사진 게시물 등록
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> savePhoto(@RequestBody RequestPhotoSaveDTO requestPhotoSaveDTO) {

        UUID photoId = photoService.savePhoto(requestPhotoSaveDTO);

        ApiResponse<Object> response = new ApiResponse<>(true, "사진 게시물 등록 성공", photoId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 사진 게시물 삭제
    @DeleteMapping
    public ResponseEntity<ApiResponse<Object>> deletePhoto(@RequestBody RequestPhotoDeleteDTO requestPhotoDeleteDTO) {

        UUID photoId = photoService.deletePhoto(requestPhotoDeleteDTO);

        ApiResponse<Object> response = new ApiResponse<>(true, "사진 게시물 삭제 성공", photoId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
