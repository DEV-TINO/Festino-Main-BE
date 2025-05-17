package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFoodBoothsGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFoodBoothGetDTO;
import com.DevTino.festino_main.booth.service.FoodBoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<ApiResponse<Object>> getFoodBooth(@PathVariable("boothId") UUID boothId){

        ResponseFoodBoothGetDTO responseFoodBoothGetDTO = foodBoothService.getFoodBooth(boothId);

        ApiResponse<Object> response = new ApiResponse<>(true, "푸드트럭 디테일 조회 성공", responseFoodBoothGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 푸드트럭 부스 전체 조회
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getFoodBooths(){

        List<ResponseFoodBoothsGetDTO> responseFoodBoothsGetDTOList = foodBoothService.getFoodBooths();

        ApiResponse<Object> response = new ApiResponse<>(true, "푸드트럭 부스 전체 조회 성공", responseFoodBoothsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}