package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFoodBoothDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFoodBoothDTO;
import com.DevTino.festino_main.booth.service.FoodBoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/booth/food")
public class FoodBoothController {

    FoodBoothService foodBoothService;

    @Autowired
    public FoodBoothController(FoodBoothService foodBoothService){
        this.foodBoothService = foodBoothService;
    }

    // 푸드트럭 디테일 조회
    @GetMapping("/{boothId}")
    public ResponseEntity<Map<String, Object>> getFoodBooth(@PathVariable UUID boothId){
        ResponseFoodBoothDTO responseFoodBoothDTO = foodBoothService.getFoodBooth(boothId);

        boolean success = (responseFoodBoothDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "푸드트럭 부스 저장 성공" : "푸드트럭 부스 저장 시 DAO 저장 실패");
        requestMap.put("boothInfo", responseFoodBoothDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 푸드트럭 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getFoodBooths(){
        List<ResponseAllFoodBoothDTO> responseAllFoodBoothDTOList = foodBoothService.getFoodBooths();

        boolean success = (responseAllFoodBoothDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 푸드트럭 조회 성공" : "전체 푸드트럭 조회 실패");
        requestMap.put("boothList", responseAllFoodBoothDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}