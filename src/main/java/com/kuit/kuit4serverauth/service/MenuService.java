package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.request.MenuSearchRequest;
import com.kuit.kuit4serverauth.dto.response.MenuSearchResponse;
import com.kuit.kuit4serverauth.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuSearchResponse> searchMenus(MenuSearchRequest request) {
        return menuRepository.findMenusByNameContaining(request.getMenuName());
    }
}
