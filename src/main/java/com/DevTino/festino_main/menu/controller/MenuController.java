package com.DevTino.festino_main.menu.controller;

import com.DevTino.festino_main.menu.domain.DTO.ResponseMenuGetDTO;
import com.DevTino.festino_main.menu.service.MenuService;
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
public class MenuController {

    MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // 부스 별 전체 메뉴 조회
    @GetMapping("/menu/all/booth/{boothId}")
    public ResponseEntity<Map<String, Object>> getMenus(@PathVariable("boothId") UUID boothId){
        List<ResponseMenuGetDTO> responseMenuGetDTOList = menuService.getMenus(boothId)  ;

        boolean success = (responseMenuGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "메뉴 저장 성공" : "메뉴 저장 시 DAO 저장 실패");
        requestMap.put("menuInfo", responseMenuGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
