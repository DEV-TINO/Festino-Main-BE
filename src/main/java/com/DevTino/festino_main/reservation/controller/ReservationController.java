package com.DevTino.festino_main.reservation.controller;

import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationGetDTO;
import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/main/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {

    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약 등록
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveReservation(@RequestBody RequestReservationSaveDTO requestReservationSaveDTO) {
        UUID reservationId = reservationService.saveReservation(requestReservationSaveDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (reservationId == null) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;

        // message, success, id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", reservationId != null);
        requestMap.put("message", (reservationId == null) ? "already reservation exist": "reservation success");
        requestMap.put("reservationId", (reservationId == null) ? "00000000-0000-0000-0000-000000000000" : reservationId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // 예약 조회
    @GetMapping
    public ResponseEntity<Map<String, Object>> getReservation(@RequestBody RequestReservationGetDTO requestReservationGetDTO) {
        ResponseReservationGetDTO responseReservationGetDTO = reservationService.getReservation(requestReservationGetDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (responseReservationGetDTO == null) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;

        // message, success, responseReservationGetDTO 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", responseReservationGetDTO != null);
        requestMap.put("message", (responseReservationGetDTO == null) ? "doesn't exist reservation history": "exist reservation history");
        requestMap.put("reservationInfo", responseReservationGetDTO);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}