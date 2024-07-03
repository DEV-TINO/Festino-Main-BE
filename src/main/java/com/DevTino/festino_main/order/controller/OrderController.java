package com.DevTino.festino_main.order.controller;

import com.DevTino.festino_main.order.model.DTO.RequestOrderSaveDTO;
import com.DevTino.festino_main.order.service.OrderService;
import com.DevTino.festino_main.reservation.model.DTO.RequestReservationSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/main")
@CrossOrigin(origins = "*")
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문 등록
    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> saveOrder(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        UUID orderId = orderService.saveOrder(requestOrderSaveDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (orderId == null) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;

        // message, success, id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", orderId != null);
        requestMap.put("message", (orderId == null) ? "order failure": "order success");
        requestMap.put("reservationId", (orderId == null) ? "00000000-0000-0000-000000000000" : orderId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
