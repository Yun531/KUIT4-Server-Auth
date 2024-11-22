package com.kuit.kuit4serverauth.interceptor;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.service.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {            //요청 처리 전 JWT 토큰의 유효성을 검증하는 인터셉터
    private final JwtUtil jwtUtil;

    public AuthInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    //요청 헤더의 Authorization에서 토큰을 추출.
    //토큰을 검증하고, 유효한 경우 요청에 username과 role을 설정.
    //인증 실패 시 CustomException 발생.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.validateToken(token);
            request.setAttribute("username", claims.getSubject());
            request.setAttribute("role", claims.get("role"));
            return true;
        }
        throw new CustomException(ErrorCode.MISSING_AUTH_HEADER);
    }
}

