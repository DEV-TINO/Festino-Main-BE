package com.DevTino.festino_main.show.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.show.domain.DTO.ResponseClubShowsGetDTO;
import com.DevTino.festino_main.show.service.ClubShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiResponse<Object>> getShows(@PathVariable("day") int day){
        List<ResponseClubShowsGetDTO> responseClubShowsGetDTOList = clubShowService.getShows(day);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "날짜 별 동아리 타임 테이블 전체 조회 성공", responseClubShowsGetDTOList);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
