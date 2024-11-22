package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {              //JWT 토큰 생성 및 검증을 담당하는 서비스 클래스
    private final String secret = "mysecretkey";   //todo 야멜 파일 환경변수 해서 가져오는 방식으로 수정
    private final long expirationMs = 3600000; // 1 hour

    //사용자 이름과 역할을 기반으로 JWT 토큰을 생성.
    //만료 시간(1시간)을 설정
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    //주어진 JWT 토큰을 검증.
    //유효하지 않은 토큰일 경우 CustomException을 던짐.
    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}