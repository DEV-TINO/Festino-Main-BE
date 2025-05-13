package com.DevTino.festino_main.config;

import com.DevTino.festino_main.auth.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = extractToken(request.getHeader("access-token"));
        String refreshToken = request.getHeader("refresh-token");

        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Access Token 유효
        if (authService.isTokenValid(accessToken)){

            String mainUserId = authService.getMainUserIdFromToken(accessToken);
            UUID mainUserUUID = UUID.fromString(mainUserId);

            // 간단한 인증 객체 생성 (권한은 ROLE_USER 하나로 예시)
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            mainUserUUID, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);}
        else {
            // Access Token 유효
            if (authService.isTokenValid(refreshToken)){
                System.out.println("refreshToken = " + refreshToken);
                String mainUserId = authService.getMainUserIdFromToken(refreshToken);

                UUID mainUserUUID = UUID.fromString(mainUserId);

                // Access + Refresh 재발급
                String newAccess = authService.createAccessToken(mainUserUUID);
                String newRefresh = authService.createRefreshToken(mainUserUUID);
                authService.saveTokens(mainUserUUID, newAccess, newRefresh);

                // 새 토큰 응답에 설정
                response.setHeader("access-token", "Bearer " + newAccess);
                response.setHeader("refresh-token", newRefresh);

                // 간단한 인증 객체 생성 (권한은 ROLE_USER 하나로 예시)
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                mainUserUUID, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            else {
                // Access + Refresh 모두 만료
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
