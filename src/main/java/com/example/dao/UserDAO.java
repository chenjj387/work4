package com.example.dao;

import com.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository // 数据访问组件
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    // 通过构造函数注入 JdbcTemplate
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 注册用户
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return rowsAffected > 0;
    }

    // 通过用户名和密码查找用户
    public User loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new UserMapper());
        } catch (Exception e) {
            // 如果未找到用户，queryForObject会抛出异常，这里捕获并返回null
            return null;
        }
    }

    // RowMapper 用于将 ResultSet 结果映射到 User 对象
    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }
}