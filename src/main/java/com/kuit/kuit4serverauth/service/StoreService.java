package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.request.StoreRequest;
import com.kuit.kuit4serverauth.dto.request.TopStoresRequest;
import com.kuit.kuit4serverauth.dto.response.StoreResponse;
import com.kuit.kuit4serverauth.dto.response.TopStoreResponse;
import com.kuit.kuit4serverauth.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<StoreResponse> getStores(StoreRequest request) {
        return storeRepository.findStores(request.getMinDeliveryPrice(), request.getStatus());
    }

    public List<TopStoreResponse> getTopStores(TopStoresRequest request) {
        return storeRepository.findTopStoresByCategory(request.getCategory(), request.getLimit());
    }
}