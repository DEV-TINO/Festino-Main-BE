package com.DevTino.festino_main.order.controller;

import com.DevTino.festino_main.order.service.TableNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<Map<String, Object>> getCustomTableNum(@RequestParam(value = "tableNumIndex") Integer tableNumIndex, @RequestParam(value = "boothId") UUID boothId) {
        String customTableNum = tableNumService.getCustomTableNum(tableNumIndex, boothId);

        // message, success, id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", customTableNum != null);
        requestMap.put("message", (customTableNum == null) ? "table num get fail": "table num get success");
        requestMap.put("tableNum", (customTableNum == null) ? "" : customTableNum);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
