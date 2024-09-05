package com.DevTino.festino_main.order.controller;

import com.DevTino.festino_main.auth.AuthService;
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
    public ResponseEntity<Map<String, Object>> saveOrder(HttpServletRequest request, @RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        Map<String, Object> requestMap = new HashMap<>();

        Bucket orderBucket = resolveOrderBucket();

        // Rate Limiting 체크
        if (!orderBucket.tryConsume(1)) {
            requestMap.put("success", false);
            requestMap.put("message", "Too many requests, please try again later");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(requestMap);
        }

        String xCsrfToken = authService.getCookieValue(request, "X-CSRF-Token");

        // 토큰 검증
        if (xCsrfToken == null || authService.isExpired(xCsrfToken)) {
            requestMap.put("success", false);
            requestMap.put("message", "Token is missing");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(requestMap);
        }

        UUID orderId = orderService.saveOrder(requestOrderSaveDTO);

        // message, success, id 값 json 데이터로 반환
        requestMap.put("success", orderId != null);
        requestMap.put("message", (orderId == null) ? "order failure": "order success");
        requestMap.put("orderId", (orderId == null) ? "00000000-0000-0000-0000-000000000000" : orderId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 주문 조회
    @GetMapping("/order")
    public ResponseEntity<Map<String, Object>> getOrder(@RequestParam("userName") String userName, @RequestParam("phoneNum") String phoneNum) {
        List<ResponseOrderGetDTO> responseOrderGetDTOList = orderService.getOrder(userName, phoneNum);

        // message, success, bills 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", !responseOrderGetDTOList.isEmpty());
        requestMap.put("message", responseOrderGetDTOList.isEmpty() ? "doesn't exist order history": "exist order history");
        requestMap.put("bills", responseOrderGetDTOList.isEmpty() ? null : responseOrderGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}