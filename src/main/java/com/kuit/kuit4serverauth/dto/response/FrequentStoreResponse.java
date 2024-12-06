package com.kuit.kuit4serverauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FrequentStoreResponse {
    private Long storeId;
    private String name;
    private Integer orderCount;
}
