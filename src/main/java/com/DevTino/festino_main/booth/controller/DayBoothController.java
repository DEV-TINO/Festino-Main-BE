package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothGetDTO;
import com.DevTino.festino_main.booth.service.DayBoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/booth/day")
public class DayBoothController {

    DayBoothService dayBoothService;

    @Autowired
    public DayBoothController(DayBoothService dayBoothService){
        this.dayBoothService = dayBoothService;
    }

    // 주간 부스 디테일 조회
    @GetMapping("/{boothId}")
    public ResponseEntity<ApiResponse<Object>> getDayBooth(@PathVariable("boothId") UUID boothId){

        ResponseDayBoothGetDTO responseDayBoothGetDTO = dayBoothService.getDayBooth(boothId);

        ApiResponse<Object> response = new ApiResponse<>(true, "주간 부스 디테일 조회 성공", responseDayBoothGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 주간 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getDayBooths(){

        List<ResponseDayBoothsGetDTO> responseDayBoothsGetDTOList = dayBoothService.getDayBooths();

        ApiResponse<Object> response = new ApiResponse<>(true, "주간 부스 전체 조회 성공", responseDayBoothsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
