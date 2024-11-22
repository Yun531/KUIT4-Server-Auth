package com.kuit.kuit4serverauth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {      //profile API: JWT 토큰을 검증하여 로그인된 사용자만 접근 가능하도록 구현합니다.
        //로그인 한 사용자면 username 이용해 "Hello, {username}" 반환하기
        String username = (String) request.getAttribute("username");

        if (username != null) {
            return ResponseEntity.ok("Hello, " + username);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {        ///admin API: 관리자 권한 (ROLE_ADMIN)이 있는 사용자만 접근 가능하도록 구현합니다.
        //role이 admin이면 "Hello, admin" 반환하기
        String role = (String) request.getAttribute("role");

        if (role != null && role.equals("ROLE_ADMIN")) {
            return ResponseEntity.ok("Hello, admin");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }
}

