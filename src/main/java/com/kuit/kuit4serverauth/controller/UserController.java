package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.UserDetail;
import com.kuit.kuit4serverauth.dto.response.FrequentStoreResponse;
import com.kuit.kuit4serverauth.dto.response.OrderResponse;
import com.kuit.kuit4serverauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(UserDetail userDetail) {
        return userService.getProfile(userDetail.getUsername());
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(UserDetail userDetail) {
        return userService.getAdmin(userDetail.getRole());
    }

    @GetMapping("/{userId}/frequent-stores")
    public ResponseEntity<List<FrequentStoreResponse>> getFrequentStores(@PathVariable Long userId) {
        List<FrequentStoreResponse> stores = userService.getFrequentStores(userId);

        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable Long userId,
                                                             @RequestParam(defaultValue = "asc") String sort) {
        List<OrderResponse> orders = userService.getUserOrders(userId, sort);

        return ResponseEntity.ok(orders);
    }
}
