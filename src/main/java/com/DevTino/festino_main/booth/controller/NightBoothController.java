package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothTossPayGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseNightBoothGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseReservationNightBoothGetDTO;
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
        ResponseNightBoothGetDTO responseNightBoothGetDTO = nightBoothService.getNightBooth(boothId);

        boolean success = (responseNightBoothGetDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "야간 부스 조회 성공" : "야간 부스 조회 실패");
        requestMap.put("boothInfo", responseNightBoothGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 예약 시 야간 부스 전체 조회
    @GetMapping("/reservation/all")
    public ResponseEntity<Map<String, Object>> getReservationNightBooths(){
        List<ResponseReservationNightBoothGetDTO> responseReservationNightBoothGetDTOList = nightBoothService.getReservationNightBooths();

        boolean success = (responseReservationNightBoothGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "야간 부스 조회 성공" : "야간 부스 조회 실패");
        requestMap.put("boothList", responseReservationNightBoothGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 야간 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getNightBooths(){
        List<ResponseNightBoothsGetDTO> responseNightBoothsGetDTOList = nightBoothService.getNightBooths();

        boolean success = (responseNightBoothsGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 야간부스 조회 성공" : "전체 야간부스 조회 실패");
        requestMap.put("boothList", responseNightBoothsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 주문 시 계좌정보 조회
    @GetMapping("/account")
    public ResponseEntity<Map<String, Object>> getAccountInfo(@RequestParam("boothId") UUID boothId) {
        Map<String, String> accountInfo = nightBoothService.getAccountInfo(boothId);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", accountInfo != null);
        requestMap.put("message", accountInfo != null ? "get account info success" : "get account info failure");
        requestMap.put("accountInfo", accountInfo);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 주문 시 토스페이 조회
    @GetMapping("/toss")
    public ResponseEntity<Map<String, Object>> getTossPayInfo(@RequestParam("boothId") UUID boothId) {
        ResponseNightBoothTossPayGetDTO responseNightBoothTossPayGetDTO = nightBoothService.getTossPayInfo(boothId);

        boolean success = responseNightBoothTossPayGetDTO != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "토스페이 조회 성공" : "토스페이 조회 실패");
        requestMap.put("tossPayInfo", responseNightBoothTossPayGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);

    }
}