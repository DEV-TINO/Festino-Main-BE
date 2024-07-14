package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseReservationNightBoothDTO;
import com.DevTino.festino_main.booth.service.NightBoothService;
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
@RequestMapping("/main/booth/night")
public class NightBoothController {

    NightBoothService nightBoothService;

    @Autowired
    public NightBoothController(NightBoothService nightBoothService){
        this.nightBoothService = nightBoothService;
    }

    // 야간 부스 디테일 조회

    @GetMapping("/{boothId}")
    public ResponseEntity<Map<String, Object>> getNightBooth(@PathVariable("boothId") UUID boothId){
        ResponseNightBoothDTO responseNightBoothDTO = nightBoothService.getNightBooth(boothId);

        boolean success = (responseNightBoothDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "야간 부스 저장 성공" : "야간 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", responseNightBoothDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 예약 시 야간 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getNightBooths(){
        List<ResponseReservationNightBoothDTO> responseReservationNightBoothDTOList = nightBoothService.getNightBooths();

        boolean success = (responseReservationNightBoothDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "야간 부스 저장 성공" : "야간 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", responseReservationNightBoothDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}