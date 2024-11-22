package com.kuit.kuit4serverauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;

    //기본 생성자가 있어야지 Jackson이 JSON 데이터를 객체로 역직렬화 할 수 있음
    //Jackson은 역직렬화 시 기본 생성자를 호출한 뒤 Setter를 사용하여 필드 값을 설정
}