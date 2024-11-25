package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.LoginReq;
import com.kuit.kuit4serverauth.dto.LoginRes;
import com.kuit.kuit4serverauth.dto.RefreshTokenReq;
import com.kuit.kuit4serverauth.dto.RefreshTokenRes;
import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.repository.UserRepository;
import com.kuit.kuit4serverauth.service.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) {      //dto 로 감싸는 형식으로 리펙토링
        String username = loginReq.getUsername();
        String password = loginReq.getPassword();

        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());
        //캐시에 refreshToken 저장, 저장구조에 들어갔다 오는게 성능에 영향이 큼 > refreshToken 자체는 접근을 많이 안해서 성능에 그나마 도움
        //쿠키에 넣으면 안되는 이유 확실하게

        LoginRes loginRes = new LoginRes(accessToken, refreshToken);

        return ResponseEntity.ok(loginRes);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenRes> refreshToken(@RequestBody RefreshTokenReq refreshTokenReq) {
        String refreshToken = refreshTokenReq.getRefreshToken();

        Claims claims = jwtUtil.validateToken(refreshToken);
        String username = claims.getSubject();

        String newAccessToken = jwtUtil.generateToken(username, (String)claims.get("role"));
        RefreshTokenRes response = new RefreshTokenRes(newAccessToken);

        return ResponseEntity.ok(response);
    }
}

