package com.kuit.kuit4serverauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private StoreResponse store;
    private OrderMenuResponse menu;
    private Integer quantity;
    private Integer totalPrice;
    private String createdDate;
}
