package com.kuit.kuit4serverauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderMenuOptionResponse {
    private Long menuOptionId;
    private String optionName;
    private Integer price;
}