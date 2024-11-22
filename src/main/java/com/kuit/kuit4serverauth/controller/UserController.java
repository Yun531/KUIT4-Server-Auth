package com.kuit.kuit4serverauth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {       //사용자 요청을 처리하는 컨트롤러로, 인증 및 권한에 따라 적절한 응답을 반환

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {                  //인증되지 않은 사용자에게 401 Unauthorized 응답을 반환
        // TODO : 로그인 한 사용자면 username 이용해 "Hello, {username}" 반환하기
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {                    //권한이 없는 사용자에게 403 Forbidden 응답을 반환하도록 구현
        // TODO: role이 admin이면 "Hello, admin" 반환하기
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }
}
