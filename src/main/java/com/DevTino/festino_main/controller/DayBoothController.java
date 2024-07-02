package com.DevTino.festino_main.controller;

import com.DevTino.festino_main.domain.DTO.ResponseDayBoothDTO;
import com.DevTino.festino_main.service.DayBoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/booth")
public class DayBoothController {

    private DayBoothService dayBoothService;

    @Autowired
    public DayBoothController(DayBoothService dayBoothService){
        this.dayBoothService = dayBoothService;
    }

    @GetMapping("/day/{boothId}")
    public ResponseEntity<Map<String, Object>> read(@PathVariable UUID boothId){
        ResponseDayBoothDTO responseDayBoothDTO = dayBoothService.read(boothId);

        boolean success = (responseDayBoothDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "주간 부스 저장 성공" : "주간 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", responseDayBoothDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
