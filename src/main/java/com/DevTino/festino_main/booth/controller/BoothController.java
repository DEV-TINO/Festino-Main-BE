package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseDayBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFoodBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothDTO;
import com.DevTino.festino_main.booth.service.BoothService;
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
@RequestMapping("/main/booth")
public class BoothController {

    BoothService boothService;

    @Autowired
    public BoothController(BoothService boothService){
        this.boothService = boothService;
    }

    // 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getBooths(){
        Map<String, List<ResponseAllBoothDTO>> boothInfo = boothService.getBooths();

        boolean success = (boothInfo == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 부스 저장 성공" : "전체 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", boothInfo);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 주간 부스 디테일 조회
    @GetMapping("/day/{boothId}")
    public ResponseEntity<Map<String, Object>> getDayBooth(@PathVariable UUID boothId){
        ResponseDayBoothDTO responseDayBoothDTO = boothService.getDayBooth(boothId);

        boolean success = (responseDayBoothDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "주간 부스 저장 성공" : "주간 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", responseDayBoothDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 푸드트럭 디테일 조회
    @GetMapping("/food/{boothId}")
    public ResponseEntity<Map<String, Object>> getFoodBooth(@PathVariable UUID boothId){
        ResponseFoodBoothDTO responseFoodBoothDTO = boothService.getFoodBooth(boothId);

        boolean success = (responseFoodBoothDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "푸드트럭 부스 저장 성공" : "푸드트럭 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", responseFoodBoothDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 야간 부스 디테일 조회
    @GetMapping("/night/{boothId}")
    public ResponseEntity<Map<String, Object>> getNightBooth(@PathVariable UUID boothId){
        ResponseNightBoothDTO responseNightBoothDTO = boothService.getNightBooth(boothId);

        boolean success = (responseNightBoothDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "야간 부스 저장 성공" : "야간 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", responseNightBoothDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
