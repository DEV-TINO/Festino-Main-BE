package com.DevTino.festino_main.notice.controller;

import com.DevTino.festino_main.notice.domain.DTO.ResponseNoticesGetDTO;
import com.DevTino.festino_main.notice.service.NoticesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/notice")
public class NoticesController {

    NoticesService noticesService;

    @Autowired
    public NoticesController(NoticesService noticesService) {
        this.noticesService = noticesService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getNotices(){
        List<ResponseNoticesGetDTO> responseNoticesGetDTOList = noticesService.getNotices()  ;

        boolean success = (responseNoticesGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 공지 저장 성공" : "전체 공지 저장 시 DAO 저장 실패");
        requestMap.put("noticesInfo", responseNoticesGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
