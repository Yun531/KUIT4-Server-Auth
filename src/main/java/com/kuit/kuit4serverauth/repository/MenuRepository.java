package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.response.MenuSearchResponse;
import com.kuit.kuit4serverauth.dto.response.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<MenuSearchResponse> findMenusByNameContaining(String menuName) {
        String sql = "SELECT m.menu_name AS menuName, m.price, " +
                        "s.store_id AS storeId, s.name AS storeName, s.status " +
                "FROM menu m JOIN stores s ON m.store_id = s.store_id " +
                "WHERE m.menu_name LIKE ? AND s.status = 'active'";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new MenuSearchResponse(
                rs.getString("menuName"),
                rs.getInt("price"),
                new StoreResponse(
                        rs.getLong("storeId"),
                        rs.getString("storeName"),
                        null,
                        rs.getString("status")
                )
        ), "%" + menuName + "%");
    }
}