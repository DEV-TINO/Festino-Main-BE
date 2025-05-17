package com.DevTino.festino_main.order.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.order.service.TableNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/main/order/table")
@CrossOrigin(origins = "*")
public class TableNumController {

    TableNumService tableNumService;

    @Autowired
    public TableNumController(TableNumService tableNumService) {
        this.tableNumService = tableNumService;
    }

    // 테이블 번호 조회
    @GetMapping("")
    public ResponseEntity<ApiResponse<Object>> getCustomTableNum(@RequestParam(value = "tableNumIndex") Integer tableNumIndex, @RequestParam(value = "boothId") UUID boothId) {
        String customTableNum = tableNumService.getCustomTableNum(tableNumIndex, boothId);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "테이블 번호 조회 성공", customTableNum);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
