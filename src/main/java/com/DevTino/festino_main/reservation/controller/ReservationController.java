package com.DevTino.festino_main.reservation.controller;

import com.DevTino.festino_main.auth.AuthService;
import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationSaveDTO;
import com.DevTino.festino_main.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/main/reservation")
public class ReservationController {

    ReservationService reservationService;
    AuthService authService;

    @Autowired
    public ReservationController(ReservationService reservationService, AuthService authService) {
        this.reservationService = reservationService;
        this.authService = authService;
    }

    // 예약 등록
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveReservation(@RequestHeader(value = "X-CSRF-Token") String token, @RequestBody RequestReservationSaveDTO requestReservationSaveDTO) throws IOException {
        Map<String, Object> requestMap = new HashMap<>();

        // 토큰 검증
        if (token == null || !authService.isExpired(token)) {
            requestMap.put("success", false);
            requestMap.put("message", "Token is missing");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(requestMap);
        }

        ResponseReservationSaveDTO responseReservationSaveDTO = reservationService.saveReservation(requestReservationSaveDTO);

        // message, success, id 값 json 데이터로 반환
        requestMap.put("success", responseReservationSaveDTO != null);
        requestMap.put("message", (responseReservationSaveDTO == null) ? "reservation failure": "reservation success");
        requestMap.put("reservationInfo", (responseReservationSaveDTO == null) ? "00000000-0000-0000-0000-000000000000" : responseReservationSaveDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 예약 조회
    @GetMapping
    public ResponseEntity<Map<String, Object>> getReservation(@RequestParam("userName") String userName, @RequestParam("phoneNum") String phoneNum) {
        ResponseReservationGetDTO responseReservationGetDTO = reservationService.getReservation(userName, phoneNum);

        // message, success, responseReservationGetDTO 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", responseReservationGetDTO != null);
        requestMap.put("message", (responseReservationGetDTO == null) ? "doesn't exist reservation history": "exist reservation history");
        requestMap.put("reservationInfo", responseReservationGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 전화번호 중복 조회
    @GetMapping("/duplication")
    public ResponseEntity<Map<String, Object>> checkReservationPhoneNum(@RequestParam("phoneNum") String phoneNum) {
        String adminName = reservationService.checkReservationPhoneNum(phoneNum);

        // message, success 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", adminName != null);
        requestMap.put("adminName", adminName);
        requestMap.put("message", adminName != null ? "exist phone number": "doesn't exist phone number");

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

}