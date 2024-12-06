package com.kuit.kuit4serverauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TopStoreResponse {
    private Long storeId;
    private String name;
    private String category;
    private Integer orderCount;
}
