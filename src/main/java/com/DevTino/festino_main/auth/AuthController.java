package com.DevTino.festino_main.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
    @GetMapping("/init")
    public ResponseEntity<Map<String, Object>> init(HttpServletResponse response) {
        String uuid = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("UUID", uuid);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", true);
        requestMap.put("message", "UUID generated and cookie set");

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // Token 생성
    @GetMapping("/token")
    public ResponseEntity<Map<String, Object>> getToken(HttpServletRequest request, HttpServletResponse response) {
        String userId = authService.getCookieValue(request, "UUID");
        Map<String, Object> requestMap = new HashMap<>();

        if (userId == null) {
            requestMap.put("success", false);
            requestMap.put("message", "UUID is missing");

            return ResponseEntity.status(HttpStatus.OK).body(requestMap);
        }

        // 토큰 생성
        String token = authService.createAccessToken(userId);

        // HTTP 응답 헤더에 토큰 추가
        response.setHeader("X-CSRF-Token", token);

        requestMap.put("success", true);
        requestMap.put("message", "Token generated");

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}