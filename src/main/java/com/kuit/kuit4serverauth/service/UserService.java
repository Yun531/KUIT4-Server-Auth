package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public ResponseEntity<String> getProfile(String username) {
        if (username == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        return ResponseEntity.ok("Hello, " + username);
    }

    public ResponseEntity<String> getAdmin(String role) {
        if ("ROLE_ADMIN".equals(role)) {
            return ResponseEntity.ok("Welcome, Admin!");
        }
        throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
    }
}
