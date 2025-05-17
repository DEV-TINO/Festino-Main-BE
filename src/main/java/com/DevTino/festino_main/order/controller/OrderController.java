package com.DevTino.festino_main.order.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.auth.AuthService;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.order.domain.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.domain.DTO.ResponseOrderGetDTO;
import com.DevTino.festino_main.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/main")
public class OrderController {
    OrderService orderService;
    AuthService authService;

    // Bucket을 캐시하여 동일한 키에 대해 같은 버킷을 사용하도록 설정
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Autowired
    public OrderController(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    // Order Rate Limiting: 1초에 3개 허용
    private Bucket resolveOrderBucket() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofSeconds(1)));
        return buckets.computeIfAbsent("order", k -> Bucket.builder().addLimit(limit).build());
    }

    // 주문 등록
    @PostMapping("/order")
    public ResponseEntity<ApiResponse<Object>> saveOrder(HttpServletRequest request, @RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {

        Bucket orderBucket = resolveOrderBucket();

        // Rate Limiting 체크
        if (!orderBucket.tryConsume(1)) {
            throw new ServiceException(ExceptionEnum.ORDER_RATE_LIMIT_EXCEEDED);
        }

        String xCsrfToken = authService.getCookieValue(request, "X-CSRF-Token");

        // 토큰 검증
        if (xCsrfToken == null) throw new ServiceException(ExceptionEnum.CSRF_TOKEN_MISSING);
        if (authService.isExpired(xCsrfToken)) throw new ServiceException(ExceptionEnum.CSRF_TOKEN_EXPIRED);

        // 주문 등록 service 실행
        UUID orderId = orderService.saveOrder(requestOrderSaveDTO);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "주문 등록 성공", orderId);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 주문 조회
    @GetMapping("/order")
    public ResponseEntity<ApiResponse<Object>> getOrder(@RequestParam("userName") String userName, @RequestParam("phoneNum") String phoneNum) {
        List<ResponseOrderGetDTO> responseOrderGetDTOList = orderService.getOrder(userName, phoneNum);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "주문 조회 성공", responseOrderGetDTOList);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}