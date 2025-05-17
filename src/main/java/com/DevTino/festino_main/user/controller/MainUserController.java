package com.DevTino.festino_main.user.controller;

import com.DevTino.festino_main.ApiResponse;
import com.DevTino.festino_main.auth.AuthService;
import com.DevTino.festino_main.user.domain.dto.RequestMainUserSaveDTO;
import com.DevTino.festino_main.user.service.MainUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<Object>> getMainUser(
            @RequestParam("phone-num") String phoneNum,
            @RequestParam("main-user-name") String mainUserName) {

        UUID mainUserId = mainUserService.getMainUser(phoneNum, mainUserName);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "유저 로그인 성공", mainUserId);

        String accessToken = authService.createAccessToken(mainUserId);
        String refreshToken = authService.createRefreshToken(mainUserId);

        // 저장소에 refresh token 저장 (예: Redis, DB 등)
        authService.saveTokens(mainUserId, accessToken, refreshToken);

        // status, header, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK)
                .header("access-token", "Bearer " + accessToken)
                .header("refresh-token", refreshToken)
                .body(response);
    }

    // 사용자 정보 저장
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveMainUser(@RequestBody RequestMainUserSaveDTO requestMainUserSaveDTO) {

        UUID mainUserId = mainUserService.saveMainUser(requestMainUserSaveDTO);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "사용자 정보 저장 성공", mainUserId);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 사용자 인증 번호 전송
    @PostMapping("/authorization")
    public ResponseEntity<ApiResponse<Object>> sendMessageMainUserAuthorization(@RequestBody RequestMainUserSaveDTO requestMainUserSaveDTO) {

        UUID mainUserId = mainUserService.sendMessageMainUserAuthorization(requestMainUserSaveDTO);

        // Map 이용해서 반환값 json 데이터로 변환
        ApiResponse<Object> response = new ApiResponse<>(true, "사용자 인증 번호 전송 성공", mainUserId );

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
