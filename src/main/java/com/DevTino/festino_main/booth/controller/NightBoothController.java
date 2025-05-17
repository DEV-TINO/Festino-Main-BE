package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.booth.domain.DTO.*;
import com.DevTino.festino_main.booth.service.NightBoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<Object>> getNightBooth(@PathVariable("boothId") UUID boothId){

        ResponseNightBoothGetDTO responseNightBoothGetDTO = nightBoothService.getNightBooth(boothId);

        ApiResponse<Object> response = new ApiResponse<>(true, "야간 부스 디테일 조회 성공", responseNightBoothGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 예약 시 야간 부스 전체 조회
    @GetMapping("/reservation/all")
    public ResponseEntity<ApiResponse<Object>> getReservationNightBooths(){

        List<ResponseReservationNightBoothGetDTO> responseReservationNightBoothGetDTOList = nightBoothService.getReservationNightBooths();

        ApiResponse<Object> response = new ApiResponse<>(true, "예약 시 야간 부스 전체 조회 성공", responseReservationNightBoothGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 야간 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getNightBooths(){

        List<ResponseNightBoothsGetDTO> responseNightBoothsGetDTOList = nightBoothService.getNightBooths();

        ApiResponse<Object> response = new ApiResponse<>(true, "야간 부스 전체 조회 성공", responseNightBoothsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 주문 시 계좌정보 조회
    @GetMapping("/account")
    public ResponseEntity<ApiResponse<Object>> getAccountInfo(@RequestParam("boothId") UUID boothId) {

        Map<String, String> accountInfo = nightBoothService.getAccountInfo(boothId);

        ApiResponse<Object> response = new ApiResponse<>(true, "주문 시 계좌정보 조회 성공", accountInfo);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 주문 시 토스페이 조회
    @GetMapping("/toss")
    public ResponseEntity<ApiResponse<Object>> getTossPayInfo(@RequestParam("boothId") UUID boothId) {

        ResponseNightBoothTossPayGetDTO responseNightBoothTossPayGetDTO = nightBoothService.getTossPayInfo(boothId);

        ApiResponse<Object> response = new ApiResponse<>(true, "주문 시 토스페이 조회 성공", responseNightBoothTossPayGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    // 주문 시 카카오페이 조회
    @GetMapping("/kakao")
    public ResponseEntity<ApiResponse<Object>> getKakaoPayInfo(@RequestParam("boothId") UUID boothId) {

        ResponseNightBoothKakaoPayGetDTO responseNightBoothKakaoPayGetDTO = nightBoothService.getKakaoPayInfo(boothId);

        ApiResponse<Object> response = new ApiResponse<>(true, "주문 시 카카오페이 조회 성공", responseNightBoothKakaoPayGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}