package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilitiesGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilityGetDTO;
import com.DevTino.festino_main.booth.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/facility")
public class FacilityController {

    FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService){
        this.facilityService = facilityService;
    }

    // 전체 편의시설 조회
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getFacilities(){

        List<ResponseFacilitiesGetDTO> responseFacilitiesGetDTOList = facilityService.getFacilities();

        ApiResponse<Object> response = new ApiResponse<>(true, "전체 편의시설 조회 성공", responseFacilitiesGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 특정 편의시설 조회
    @GetMapping("/{boothId}")
    public ResponseEntity<ApiResponse<Object>> getFacility(@PathVariable("boothId") UUID boothId){

        ResponseFacilityGetDTO responseFacilityGetDTO = facilityService.getFacility(boothId);

        ApiResponse<Object> response = new ApiResponse<>(true, "특정 편의시설 조회 성공", responseFacilityGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
