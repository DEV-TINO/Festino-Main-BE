package com.DevTino.festino_main.reservation.controller;

import com.DevTino.festino_main.reservation.model.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.model.ReservationDAO;
import com.DevTino.festino_main.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class ReservationController {

    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약 등록하기
    @PostMapping("/main/reservation")
    public ResponseEntity<Map<String, Object>> createReservation(@RequestBody RequestReservationSaveDTO requestReservationSaveDTO) {
        UUID reservationId = reservationService.createReservation(requestReservationSaveDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (reservationId == null) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;

        // message, success, id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", reservationId != null);
        requestMap.put("message", (reservationId == null) ? "already reservation exist": "reservation success");
        requestMap.put("reservationId", (reservationId == null) ? "00000000-0000-0000-000000000000" : reservationId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // 예약 조회하기
    @GetMapping("/main/reservation/all/phone/{phoneNum}")
    public ResponseEntity<Map<String, Object>> searchReservation(@PathVariable("phoneNum") String phoneNum) {
        ReservationDAO reservationDAO = reservationService.searchReservation(phoneNum);

        // HTTP 상태 반환
        HttpStatus httpStatus = (reservationDAO == null) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;

        // message, success, id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", reservationDAO != null);
        requestMap.put("message", (reservationDAO == null) ? "already reservation exist": "reservation success");
        requestMap.put("reservationInfo", reservationDAO);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}