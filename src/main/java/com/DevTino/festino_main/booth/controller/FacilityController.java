package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilitiesGetDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilityGetDTO;
import com.DevTino.festino_main.booth.service.FacilityService;
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
@RequestMapping("/main/facility")
public class FacilityController {

    FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService){
        this.facilityService = facilityService;
    }

    // 전체 편의시설 조회
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getFacilities(){
        List<ResponseFacilitiesGetDTO> responseFacilitiesGetDTOList = facilityService.getFacilities();

        boolean success = (responseFacilitiesGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 편의시설 조회 성공" : "전체 편의시설 조회 실패");
        requestMap.put("facilityList", responseFacilitiesGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 특정 편의시설 조회
    @GetMapping("/{boothId}")
    public ResponseEntity<Map<String, Object>> getFacility(@PathVariable("boothId") UUID boothId){
        ResponseFacilityGetDTO responseFacilityGetDTO = facilityService.getFacility(boothId);

        boolean success = (responseFacilityGetDTO == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "특정 편의시설 조회 성공" : "특정 편의시설 조회 실패");
        requestMap.put("facility", responseFacilityGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
