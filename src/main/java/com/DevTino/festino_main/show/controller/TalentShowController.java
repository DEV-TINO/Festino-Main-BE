package com.DevTino.festino_main.show.controller;

import com.DevTino.festino_main.show.domain.DTO.ResponseTalentShowsGetDTO;
import com.DevTino.festino_main.show.service.TalentShowService;
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
public class TalentShowController {

    TalentShowService talentShowService;

    @Autowired
    public TalentShowController(TalentShowService talentShowService){
        this.talentShowService = talentShowService;
    }

    // 날짜 별 연예인 전체 조회
    @GetMapping("/talent/all/date/{year}/{month}/{day}")
    public ResponseEntity<Map<String, Object>> getShows(@PathVariable int day){
        List<ResponseTalentShowsGetDTO> responseTalentShowsGetDTOList = talentShowService.getShows(day);

        boolean success = (responseTalentShowsGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "연예인 타임 테이블 저장 성공" : "연예인 타임 테이블 저장 시 DAO 저장 실패");
        requestMap.put("showInfo", responseTalentShowsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
