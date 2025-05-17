package com.DevTino.festino_main.show.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.show.domain.DTO.ResponseTalentShowsGetDTO;
import com.DevTino.festino_main.show.service.TalentShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/main")
public class TalentShowController {

    TalentShowService talentShowService;

    @Autowired
    public TalentShowController(TalentShowService talentShowService){
        this.talentShowService = talentShowService;
    }

    // 날짜 별 연예인 타임 테이블 전체 조회
    @GetMapping("/talent/all/date/{day}")
    public ResponseEntity<ApiResponse<Object>> getShows(@PathVariable("day") int day){
        List<ResponseTalentShowsGetDTO> responseTalentShowsGetDTOList = talentShowService.getShows(day);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "날짜 별 연예인 타임 테이블 전체 조회 성공", responseTalentShowsGetDTOList);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
