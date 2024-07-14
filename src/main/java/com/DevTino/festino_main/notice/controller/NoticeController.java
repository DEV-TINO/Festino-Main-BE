package com.DevTino.festino_main.notice.controller;

import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticeGetDTO;
import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticesGetDTO;
import com.DevTino.festino_main.notice.service.NoticeService;
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
@RequestMapping("/main/notice")
public class NoticeController {

    NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService){
        this.noticeService = noticeService;
    }

    // 공지 조회
    @GetMapping("/{noticeId}")
    public ResponseEntity<Map<String, Object>> getNotice(@PathVariable UUID noticeId){
        ResponseNoticeGetDTO responseNoticeGetDTO = noticeService.getNotice(noticeId);

        boolean success = (responseNoticeGetDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "공지 저장 성공" : "공지 저장 시 DAO 저장 실패");
        requestMap.put("noticeInfo", responseNoticeGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getNotices(){
        List<ResponseNoticesGetDTO> responseNoticesGetDTOList = noticeService.getNotices()  ;

        boolean success = (responseNoticesGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 공지 저장 성공" : "전체 공지 저장 시 DAO 저장 실패");
        requestMap.put("noticesInfo", responseNoticesGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 가장 최근 공지 조회 - pin
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getRecentNotice(){
        ResponseNoticeGetDTO responseNoticeGetDTO = noticeService.getRecentNotice();

        boolean success = (responseNoticeGetDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "최근 공지 1개 성공" : "최근 공지 1개 실패");
        requestMap.put("noticeInfo", responseNoticeGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

}
