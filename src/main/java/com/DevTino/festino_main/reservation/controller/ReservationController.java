package com.DevTino.festino_main.reservation.controller;

import com.DevTino.festino_main.reservation.domain.DTO.RequestCreateReservationDTO;
import com.DevTino.festino_main.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Map<String, Object>> createReservation(@RequestBody RequestCreateReservationDTO requestCreateReservationDTO) {
        UUID reservationId = reservationService.createReservation(requestCreateReservationDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (reservationId == null) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;

        // message, success, id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", reservationId != null);
        requestMap.put("message", (reservationId == null) ? "error": "success");

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}