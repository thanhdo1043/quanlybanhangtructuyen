package com.departmentstore.dao;

import com.departmentstore.model.User;
import com.departmentstore.util.DatabaseConnection;
import java.sql.*;

public class UserDao {
    
    public User findById(int userId) {
        User user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                user = mapResultSetToUser(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    public User findByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND is_active = TRUE";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                user = mapResultSetToUser(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setAddress(rs.getString("address"));
        user.setUserType(User.UserType.valueOf(rs.getString("user_type")));
        user.setRegistrationDate(rs.getTimestamp("registration_date").toLocalDateTime());
        user.setActive(rs.getBoolean("is_active"));
        return user;
    }
}