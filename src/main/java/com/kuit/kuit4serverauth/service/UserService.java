package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.response.FrequentStoreResponse;
import com.kuit.kuit4serverauth.dto.response.OrderResponse;
import com.kuit.kuit4serverauth.exception.CustomException;
import com.kuit.kuit4serverauth.exception.ErrorCode;
import com.kuit.kuit4serverauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<String> getProfile(String username) {
        if (username == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        return ResponseEntity.ok("Hello, " + username);
    }

    public ResponseEntity<String> getAdmin(String role) {
        if ("ROLE_ADMIN".equals(role)) {
            return ResponseEntity.ok("Welcome, Admin!");
        }
        throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
    }

    public List<FrequentStoreResponse> getFrequentStores(Long userId) {
        return userRepository.findFrequentStores(userId);
    }


    public List<OrderResponse> getUserOrders(Long userId, String sort) {
        return userRepository.findOrdersByUserId(userId, sort);
    }
}
