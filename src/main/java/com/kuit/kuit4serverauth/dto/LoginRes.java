package com.kuit.kuit4serverauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRes {
    private String accessToken;
    private String refreshToken;

    public LoginRes(){};

    public LoginRes(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}