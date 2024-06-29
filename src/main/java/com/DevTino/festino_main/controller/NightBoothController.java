package com.DevTino.festino_main.controller;

import com.DevTino.festino_main.domain.DTO.ResponseNightBoothDTO;
import com.DevTino.festino_main.service.NightBoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/booth")
public class NightBoothController {

    private NightBoothService nightBoothService;

    @Autowired
    public NightBoothController(NightBoothService nightBoothService){
        this.nightBoothService = nightBoothService;
    }

    @GetMapping("day/{boothId}")
    public ResponseEntity<Map<String, Object>> read(@PathVariable UUID boothId){
        ResponseNightBoothDTO responseNightBoothDTO = nightBoothService.read(boothId);

        boolean success = (responseNightBoothDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "야간 부스 저장 성공" : "야간 부스 저장 시 DAO 저장 실패");
        requestMap.put("responseNightBoothDTO", responseNightBoothDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

}