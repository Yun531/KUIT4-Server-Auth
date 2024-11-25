package com.kuit.kuit4serverauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenReq {
    private String refreshToken;

    public RefreshTokenReq() {}

    public RefreshTokenReq(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
