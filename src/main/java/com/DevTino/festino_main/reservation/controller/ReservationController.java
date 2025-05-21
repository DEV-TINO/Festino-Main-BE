package com.DevTino.festino_main.reservation.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.auth.AuthService;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.reservation.domain.DTO.RequestReservationSaveDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationGetDTO;
import com.DevTino.festino_main.reservation.domain.DTO.ResponseReservationSaveDTO;
import com.DevTino.festino_main.reservation.service.ReservationService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/main/reservation")
public class ReservationController {
    ReservationService reservationService;
    AuthService authService;

    // Bucket을 캐시하여 동일한 키에 대해 같은 버킷을 사용하도록 설정
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Autowired
    public ReservationController(ReservationService reservationService, AuthService authService) {
        this.reservationService = reservationService;
        this.authService = authService;
    }

    // Reservation Rate Limiting: 1초에 100개 허용
    private Bucket resolveReservationBucket() {
        Bandwidth limit = Bandwidth.classic(100, Refill.intervally(100, Duration.ofSeconds(1)));
        return buckets.computeIfAbsent("reservation", k -> Bucket.builder().addLimit(limit).build());
    }

    // 예약 등록
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveReservation(HttpServletRequest request, @RequestBody RequestReservationSaveDTO requestReservationSaveDTO) {

        Bucket reservationBucket = resolveReservationBucket();

        // Rate Limiting 체크
        if (!reservationBucket.tryConsume(1)) {
            throw new ServiceException(ExceptionEnum.ORDER_RATE_LIMIT_EXCEEDED);
        }

        String xCsrfToken = authService.getCookieValue(request, "X-CSRF-Token");

        // 토큰 검증
        if (xCsrfToken == null) throw new ServiceException(ExceptionEnum.CSRF_TOKEN_MISSING);
        if (authService.isExpired(xCsrfToken)) throw new ServiceException(ExceptionEnum.CSRF_TOKEN_EXPIRED);

        // 예약 등록 service 실행
        ResponseReservationSaveDTO responseReservationSaveDTO = reservationService.saveReservation(requestReservationSaveDTO);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "예약 등록 성공", responseReservationSaveDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 예약 조회
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getReservation(@RequestParam("userName") String userName, @RequestParam("phoneNum") String phoneNum) {
        ResponseReservationGetDTO responseReservationGetDTO = reservationService.getReservation(userName, phoneNum);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "예약 조회 성공", responseReservationGetDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 전화번호 중복 조회
    @GetMapping("/duplication")
    public ResponseEntity<ApiResponse<Object>> checkReservationPhoneNum(@RequestParam("phoneNum") String phoneNum) {
        String adminName = reservationService.checkReservationPhoneNum(phoneNum);

        ApiResponse<Object> response;

        // Map 이용해서 반환값 json 데이터로 변환
        if (adminName.equals("-1")) {
            response = new ApiResponse<>(true, "전화번호 중복 조회 실패", null);
        } else {
            response = new ApiResponse<>(true, "전화번호 중복 조회 성공", adminName);
        }

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}