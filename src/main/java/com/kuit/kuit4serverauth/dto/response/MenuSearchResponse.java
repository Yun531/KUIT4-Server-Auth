package com.kuit.kuit4serverauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuSearchResponse {
    private String menuName;
    private Integer price;
    private StoreResponse store; // 기존 StoreResponse 재사용
}
