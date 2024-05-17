package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/caftansshop";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Login authenticate(String email, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Email, Password FROM login WHERE Email = ? AND Password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Login(rs.getString("Email"), rs.getString("Password"));
                }
            }
        }
        return null;
    }


    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void resetPassword(String email, String newPassword) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE login SET Password = ? WHERE Email = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, email);
            stmt.executeUpdate();
        }
    }
}
