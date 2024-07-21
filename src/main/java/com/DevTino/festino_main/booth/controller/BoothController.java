package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllBoothDTO;
import com.DevTino.festino_main.booth.service.BoothService;
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
public class BoothController {

    BoothService boothService;

    @Autowired
    public BoothController(BoothService boothService){
        this.boothService = boothService;
    }

    // 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getBooths(){
        List<ResponseAllBoothDTO> boothInfo = boothService.getBooths();

        boolean success = (boothInfo == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 부스 조회 성공" : "전체 부스 조회 실패");
        requestMap.put("boothList", boothInfo);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
