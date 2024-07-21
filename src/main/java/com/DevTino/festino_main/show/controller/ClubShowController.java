package com.DevTino.festino_main.show.controller;

import com.DevTino.festino_main.show.domain.DTO.ResponseClubShowsGetDTO;
import com.DevTino.festino_main.show.service.ClubShowService;
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
public class ClubShowController {

    ClubShowService clubShowService;

    @Autowired
    public ClubShowController(ClubShowService clubShowService){
        this.clubShowService = clubShowService;
    }

    // 날짜 별 동아리 타임 테이블 전체 조회
    @GetMapping("/club/all/date/{day}")
    public ResponseEntity<Map<String, Object>> getShows(@PathVariable("day") int day){
        List<ResponseClubShowsGetDTO> responseClubShowsGetDTOList = clubShowService.getShows(day);

        boolean success = (responseClubShowsGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "동아리 타임 테이블 성공" : "동아리 타임 테이블 실패");
        requestMap.put("showInfo", responseClubShowsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
