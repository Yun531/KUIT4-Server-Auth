package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.request.StoreRequest;
import com.kuit.kuit4serverauth.dto.request.TopStoresRequest;
import com.kuit.kuit4serverauth.dto.response.StoreResponse;
import com.kuit.kuit4serverauth.dto.response.TopStoreResponse;
import com.kuit.kuit4serverauth.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreResponse>> getStores(@ModelAttribute StoreRequest request) {
        List<StoreResponse> stores = storeService.getStores(request);

        return ResponseEntity.ok(stores);
    }

    @GetMapping("/top")
    public ResponseEntity<List<TopStoreResponse>> getTopStores(@ModelAttribute TopStoresRequest request) {
        List<TopStoreResponse> topStores = storeService.getTopStores(request);

        return ResponseEntity.ok(topStores);
    }
}
