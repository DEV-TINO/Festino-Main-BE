package com.DevTino.festino_main.auth;

import com.DevTino.festino_main.DateTimeUtils;
import com.DevTino.festino_main.exception.ExceptionEnum;
import com.DevTino.festino_main.exception.ServiceException;
import com.DevTino.festino_main.user.domain.entity.MainUserTokenDAO;
import com.DevTino.festino_main.user.repository.MainUserTokenRepositoryJPA;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {

    MainUserTokenRepositoryJPA mainUserTokenRepositoryJPA;

    @Autowired
    public AuthService(MainUserTokenRepositoryJPA mainUserTokenRepositoryJPA) {
        this.mainUserTokenRepositoryJPA = mainUserTokenRepositoryJPA;
    }

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    // 토큰 생성
    public String createAccessToken(String userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰 생성
    public String createAccessToken(UUID userId) {
        return Jwts.builder()
                .claim("mainUserId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 만료 확인
    public boolean isExpired(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    // 쿠키 찾기
    public String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) throw new ServiceException(ExceptionEnum.NO_COOKIES_PRESENT);

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        throw new ServiceException(ExceptionEnum.COOKIE_NOT_FOUND_BY_NAME);
    }

    public String createRefreshToken(UUID userId) {
        return Jwts.builder()
                .claim("mainUserId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24)) // 1 day
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            return !isExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String getMainUserIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("mainUserId", String.class);
    }

    public void saveTokens(UUID mainUserId, String accessToken, String refreshToken) {
        MainUserTokenDAO mainUserToken = MainUserTokenDAO.builder()
                .tokenId(UUID.randomUUID())
                .mainUserId(mainUserId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .createAt(DateTimeUtils.nowZone())
                .build();

        mainUserTokenRepositoryJPA.save(mainUserToken);
    }

}