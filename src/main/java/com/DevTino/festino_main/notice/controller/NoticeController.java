package com.DevTino.festino_main.notice.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticeGetDTO;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticesGetDTO;
import com.DevTino.festino_main.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/notice")
public class NoticeController {

    NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService){
        this.noticeService = noticeService;
    }

    // 공지 조회
    @GetMapping("/{noticeId}")
    public ResponseEntity<ApiResponse<Object>> getNotice(@PathVariable("noticeId") UUID noticeId){

        ResponseNoticeGetDTO responseNoticeGetDTO = noticeService.getNotice(noticeId);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "공지 조회 성공", responseNoticeGetDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 공지 전체 조회
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getNotices(){

        List<ResponseNoticesGetDTO> responseNoticesGetDTOList = noticeService.getNotices();

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "공지 전체 조회 성공", responseNoticesGetDTOList);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 가장 최근 공지 조회 - pin
    @GetMapping("")
    public ResponseEntity<ApiResponse<Object>> getRecentNotice(){

        ResponseNoticeGetDTO responseNoticeGetDTO = noticeService.getRecentNotice();

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "가장 최근 공지 조회 - pin 성공", responseNoticeGetDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
