package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.response.*;
import com.kuit.kuit4serverauth.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    public List<FrequentStoreResponse> findFrequentStores(Long userId) {
        String sql = "SELECT s.store_id AS storeId, s.name, COUNT(o.order_id) AS orderCount " +
                "FROM orders o JOIN stores s ON o.store_id = s.store_id " +
                "WHERE o.user_id = ? GROUP BY s.store_id, s.name HAVING COUNT(o.order_id) >= 2";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new FrequentStoreResponse(
                rs.getLong("storeId"),
                rs.getString("name"),
                rs.getInt("orderCount")
        ), userId);
    }

    public List<OrderResponse> findOrdersByUserId(Long userId, String sort) {
        String sql =  "SELECT o.order_id AS orderId, s.store_id AS storeId, s.name AS storeName, " +
                        "m.menu_id AS menuId, m.menu_name AS menuName, m.price AS menuPrice, " +
                        "mo.menu_option_id AS menuOptionId, mo.option AS optionName, mo.price AS optionPrice, " +
                        "o.quantity, o.total_price AS totalPrice, o.created_date AS createdDate " +
                "FROM orders o " +
                    "JOIN stores s ON o.store_id = s.store_id " +
                    "JOIN menu m ON o.menu_id = m.menu_id " +
                    "LEFT JOIN menu_option mo ON o.menu_option_id = mo.menu_option_id " +
                "WHERE o.user_id = ? " +
                "ORDER BY o.total_price " + sort.toUpperCase();

        return jdbcTemplate.query(sql, (rs, rowNum) -> new OrderResponse(
                rs.getLong("orderId"),
                new StoreResponse(
                        rs.getLong("storeId"),
                        rs.getString("storeName"),
                        null,
                        null
                ),
                new OrderMenuResponse(
                        rs.getLong("menuId"),
                        rs.getString("menuName"),
                        rs.getInt("menuPrice"),
                        rs.getObject("menuOptionId") != null ? new OrderMenuOptionResponse(
                                rs.getLong("menuOptionId"),
                                rs.getString("optionName"),
                                rs.getInt("optionPrice")
                        ) : null
                ),
                rs.getInt("quantity"),
                rs.getInt("totalPrice"),
                rs.getString("createdDate")
        ), userId);
    }
}
