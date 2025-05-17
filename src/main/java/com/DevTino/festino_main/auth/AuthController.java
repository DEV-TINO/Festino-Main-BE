package com.DevTino.festino_main.auth;

import com.DevTino.festino_main.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/main/auth")
public class AuthController {
    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 고유 UUID 생성
    @PostMapping("/init")
    public ResponseEntity<ApiResponse<Object>> init(HttpServletResponse response) {
        String uuid = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("UUID", uuid);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        ApiResponse<Object> apiResponse = new ApiResponse<>(true, "UUID generated and cookie set", null);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Token 생성
    @PostMapping("/token")
    public ResponseEntity<ApiResponse<Object>> getToken(HttpServletRequest request, HttpServletResponse response) {
        String userId = authService.getCookieValue(request, "UUID");
        ApiResponse<Object> apiResponse;

        // 토큰 생성
        String token = authService.createAccessToken(userId);

        // HTTP 응답 헤더에 토큰 추가
        Cookie cookie = new Cookie("X-CSRF-Token", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60);
        response.addCookie(cookie);

        // response.setHeader("X-CSRF-Token", token);
        apiResponse = new ApiResponse<>(true, "Token generated", null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}