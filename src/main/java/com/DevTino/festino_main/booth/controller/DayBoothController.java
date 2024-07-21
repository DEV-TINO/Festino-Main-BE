package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllDayBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothDTO;
import com.DevTino.festino_main.booth.service.DayBoothService;
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
@RequestMapping("/main/booth/day")
public class DayBoothController {

    DayBoothService dayBoothService;

    @Autowired
    public DayBoothController(DayBoothService dayBoothService){
        this.dayBoothService = dayBoothService;
    }

    // 주간 부스 디테일 조회
    @GetMapping("/{boothId}")
    public ResponseEntity<Map<String, Object>> getDayBooth(@PathVariable UUID boothId){
        ResponseDayBoothDTO responseDayBoothDTO = dayBoothService.getDayBooth(boothId);

        boolean success = (responseDayBoothDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "주간 부스 저장 성공" : "주간 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", responseDayBoothDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 주간 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getDayBooths(){
        List<ResponseAllDayBoothDTO> responseAllDayBoothDTOList = dayBoothService.getDayBooths();

        boolean success = (responseAllDayBoothDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 주간부스 조회 성공" : "전체 주간부스 조회 실패");
        requestMap.put("boothList", responseAllDayBoothDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
