package com.kuit.kuit4serverauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreRequest {
    private Integer minDeliveryPrice;
    private String status;
}