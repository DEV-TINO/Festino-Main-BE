package com.DevTino.festino_main.show.controller;

import com.DevTino.festino_main.show.domain.DTO.ResponseClubShowsGetDTO;
import com.DevTino.festino_main.show.domain.DTO.ResponseTalentShowsGetDTO;
import com.DevTino.festino_main.show.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/main")
public class ShowController {

    ShowService showService;

    @Autowired
    public ShowController(ShowService showService){
        this.showService = showService;
    }

    // 날짜 별 동아리 타임 테이블 전체 조회
    @GetMapping("/club/all/date/{day}")
    public ResponseEntity<Map<String, Object>> getClubShow(@PathVariable int day){
        List<ResponseClubShowsGetDTO> responseClubShowsGetDTOList = showService.getClubShow(day);

        boolean success = (responseClubShowsGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "동아리 타임 테이블 저장 성공" : "동아리 타임 테이블 저장 시 DAO 저장 실패");
        requestMap.put("showInfo", responseClubShowsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 날짜 별 연예인 타임 테이블 전체 조회
    @GetMapping("/talent/all/date/{day}")
    public ResponseEntity<Map<String, Object>> getTalentShow(@PathVariable int day){
        List<ResponseTalentShowsGetDTO> responseTalentShowsGetDTOList = showService.getTalentShow(day);

        boolean success = (responseTalentShowsGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "연예인 타임 테이블 저장 성공" : "연예인 타임 테이블 저장 시 DAO 저장 실패");
        requestMap.put("showInfo", responseTalentShowsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
