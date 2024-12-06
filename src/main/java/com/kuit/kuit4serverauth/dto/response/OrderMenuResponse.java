package com.kuit.kuit4serverauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderMenuResponse {
    private Long menuId;
    private String menuName;
    private Integer price;
    private OrderMenuOptionResponse option;
}
