package com.DevTino.festino_main.booth.controller;

import com.DevTino.festino_main.booth.domain.DTO.ResponseAllFacilityDTO;
import com.DevTino.festino_main.booth.domain.DTO.ResponseFacilityDTO;
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
@RequestMapping("/main")
public class FacilityController {

    FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService){
        this.facilityService = facilityService;
    }

    // 전체 편의시설 조회
    @GetMapping("/facility/all")
    public ResponseEntity<Map<String, Object>> getAmenities(){
        List<ResponseAllFacilityDTO> responseAllFacilityDTOList = facilityService.getAmenities();

        boolean success = (responseAllFacilityDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "전체 편의시설 조회 성공" : "전체 편의시설 조회 실패");
        requestMap.put("boothList", responseAllFacilityDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

}
