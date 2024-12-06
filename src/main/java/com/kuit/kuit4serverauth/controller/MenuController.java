package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.request.MenuSearchRequest;
import com.kuit.kuit4serverauth.dto.response.MenuSearchResponse;
import com.kuit.kuit4serverauth.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/search")
    public ResponseEntity<List<MenuSearchResponse>> searchMenus(@ModelAttribute MenuSearchRequest request) {
        List<MenuSearchResponse> menus = menuService.searchMenus(request);

        return ResponseEntity.ok(menus);
    }
}
