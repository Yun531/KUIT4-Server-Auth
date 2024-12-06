package com.kuit.kuit4serverauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {
    String username;
    String password;
}