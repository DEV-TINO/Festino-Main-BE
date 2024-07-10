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
public class ClubShowsController {

    ClubShowService clubShowService;

    @Autowired
    public ClubShowsController(ClubShowService clubShowService){
        this.clubShowService = clubShowService;
    }

    //@GetMapping("/club/all/date/{showDate}")
    @GetMapping("/club/all/date/{year}/{month}/{day}")
    public ResponseEntity<Map<String, Object>> getShow(@PathVariable int day){
        List<ResponseClubShowsGetDTO> responseClubShowsGetDTOList = clubShowService.getShow(day);

        boolean success = (responseClubShowsGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "동아리 타임 테이블 저장 성공" : "동아리 타임 테이블 저장 시 DAO 저장 실패");
        requestMap.put("showInfo", responseClubShowsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
