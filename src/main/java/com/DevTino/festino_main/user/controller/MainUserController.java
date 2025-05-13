package com.DevTino.festino_main.user.controller;

import com.DevTino.festino_main.auth.AuthService;
import com.DevTino.festino_main.user.domain.dto.RequestMainUserSaveDTO;
import com.DevTino.festino_main.user.service.MainUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/main/user")
public class MainUserController {

    MainUserService mainUserService;
    AuthService authService;

    @Autowired
    public MainUserController(MainUserService mainUserService, AuthService authService) {
        this.mainUserService = mainUserService;
        this.authService = authService;
    }

    // 유저 로그인
    @GetMapping
    public ResponseEntity<Map<String, Object>> getMainUser(
            @RequestParam("phone-num") String phoneNum,
            @RequestParam("main-user-name") String mainUserName) {

        UUID mainUserId = mainUserService.getMainUser(phoneNum, mainUserName);

        boolean success = mainUserId != null;

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "로그인 성공" : "로그인 실패");
        response.put("mainUserId", mainUserId);

        if (success) {
            String accessToken = authService.createAccessToken(mainUserId);
            String refreshToken = authService.createRefreshToken(mainUserId);

            // 저장소에 refresh token 저장 (예: Redis, DB 등)
            authService.saveTokens(mainUserId, accessToken, refreshToken);

            return ResponseEntity.ok()
                    .header("access-token", "Bearer " + accessToken)
                    .header("refresh-token", refreshToken)
                    .body(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // 사용자 정보 저장
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveMainUser(@RequestBody RequestMainUserSaveDTO requestMainUserSaveDTO) {

        UUID mainUserId = mainUserService.saveMainUser(requestMainUserSaveDTO);

        boolean success = mainUserId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "사용자 정보 저장 성공" : "사용자 정보 저장 실패");
        requestMap.put("mainUserId", mainUserId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 사용자 인증 번호 전송
    @PostMapping("/authorization")
    public ResponseEntity<Map<String, Object>> sendMessageMainUserAuthorization(@RequestBody RequestMainUserSaveDTO requestMainUserSaveDTO) {

        UUID mainUserId = mainUserService.sendMessageMainUserAuthorization(requestMainUserSaveDTO);

        boolean success = mainUserId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "인증번호 전송 성공" : "인증번호 전송 실패");
        requestMap.put("mainUserId", mainUserId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
