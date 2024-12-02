package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.UserDetail;
import com.kuit.kuit4serverauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(UserDetail userDetail) {
        return userService.getProfile(userDetail.getUsername());
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(UserDetail userDetail) {
        return userService.getAdmin(userDetail.getRole());
    }
}
