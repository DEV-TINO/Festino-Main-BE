package com.DevTino.festino_main.event.photo.controller;

import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoDeleteDTO;
import com.DevTino.festino_main.event.photo.domain.dto.RequestPhotoSaveDTO;
import com.DevTino.festino_main.event.photo.domain.dto.ResponsePhotoGetDTO;
import com.DevTino.festino_main.event.photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    // 사진 게시물 전체 조회
    @GetMapping("all/{type}") // type : new, heart
    public ResponseEntity<Map<String, Object>> getPhotos(@PathVariable("type") String type) {

        // 사진 게시물 전체 조회 service 실행
        List<ResponsePhotoGetDTO> responsePhotoGetDTOList = photoService.getPhotos(type);

        // 사진 게시물 전체 조회 성공 여부 설정
        boolean success = responsePhotoGetDTOList != null;

        // Map 이용해서 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "사진 게시물 조회 성공" : "사진 게시물 조회 시 DAO 검색 실패");
        requestMap.put("photoInfo", responsePhotoGetDTOList);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 유저 사진 게시물 전체 조회
    @GetMapping("all/{type}/user/{mainUserId}")
    public ResponseEntity<Map<String, Object>> getUserPhotos(@PathVariable("type") String type, @PathVariable("mainUserId") UUID mainUserId) {

        // 사진 게시물 전체 조회 service 실행
        List<ResponsePhotoGetDTO> responsePhotoGetDTOList = photoService.getMainUserPhotos(mainUserId, type);

        // 사진 게시물 전체 조회 성공 여부 설정
        boolean success = responsePhotoGetDTOList != null;

        // Map 이용해서 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "사진 게시물 조회 성공" : "사진 게시물 조회 시 DAO 검색 실패");
        requestMap.put("photoInfo", responsePhotoGetDTOList);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 사진 게시물 등록
    @PostMapping
    public ResponseEntity<Map<String, Object>> savePhoto(@RequestBody RequestPhotoSaveDTO requestPhotoSaveDTO) {

        UUID photoId = photoService.savePhoto(requestPhotoSaveDTO);

        boolean success = photoId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "사진 게시물 저장 성공" : "사진 게시물 저장 실패");
        requestMap.put("photoId", photoId);

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
