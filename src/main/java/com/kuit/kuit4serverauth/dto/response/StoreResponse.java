package com.kuit.kuit4serverauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreResponse {
    private Long storeId;
    private String name;
    private Integer minDeliveryPrice;
    private String status;
}