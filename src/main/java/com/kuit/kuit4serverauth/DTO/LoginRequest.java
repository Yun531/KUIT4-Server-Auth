package com.kuit.kuit4serverauth.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {
    String username;
    String password;
}
