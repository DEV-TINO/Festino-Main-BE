package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.booth.domain.DTO.ResponseBoothsGetDTO;
import com.DevTino.festino_main.booth.service.BoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiResponse<Object>> getBooths(){

        List<ResponseBoothsGetDTO> boothInfo = boothService.getBooths();

        ApiResponse<Object> response = new ApiResponse<>(true, "부스 전체 조회 성공", boothInfo);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
