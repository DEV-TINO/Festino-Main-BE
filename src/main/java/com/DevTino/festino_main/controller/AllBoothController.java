package com.DevTino.festino_main.controller;

import com.DevTino.festino_main.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.service.AllBoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/booth")
public class AllBoothController {

    AllBoothService categoryBoothService;

    @Autowired
    public AllBoothController(AllBoothService categoryBoothService){
        this.categoryBoothService = categoryBoothService;
    }

    // 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> read(){
        Map<String, List<ResponseAllBoothDTO>> stringListMap = categoryBoothService.read();

        boolean success = (stringListMap == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 부스 저장 성공" : "전체 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", stringListMap);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
