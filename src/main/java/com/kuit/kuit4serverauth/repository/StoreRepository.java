package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.response.StoreResponse;
import com.kuit.kuit4serverauth.dto.response.TopStoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<StoreResponse> findStores(Integer minDeliveryPrice, String status) {
        String sql = "SELECT store_id AS storeId, name, min_delivery_price AS minDeliveryPrice, status " +
                "FROM stores WHERE status = ? AND min_delivery_price >= ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StoreResponse.class), status, minDeliveryPrice);
    }

    public List<TopStoreResponse> findTopStoresByCategory(String category, int limit) {
        String sql = "SELECT s.store_id AS storeId, s.name, s.category, COUNT(o.order_id) AS orderCount " +
                "FROM stores s JOIN orders o ON s.store_id = o.store_id " +
                "WHERE s.category = ? GROUP BY s.store_id, s.name, s.category " +
                "ORDER BY COUNT(o.order_id) DESC LIMIT ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new TopStoreResponse(
                rs.getLong("storeId"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getInt("orderCount")
        ), category, limit);
    }
}
