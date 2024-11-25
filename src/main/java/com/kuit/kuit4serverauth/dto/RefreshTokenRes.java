package com.kuit.kuit4serverauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRes {
    private String accessToken;

    public RefreshTokenRes(){
    }
    public RefreshTokenRes(String accessToken) {
        this.accessToken = accessToken;
    }
}
