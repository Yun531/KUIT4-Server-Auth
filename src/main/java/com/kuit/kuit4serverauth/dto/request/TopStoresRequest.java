package com.kuit.kuit4serverauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TopStoresRequest {
    private String category;
    private Integer limit;
}
