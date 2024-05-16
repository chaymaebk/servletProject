/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author TechAima
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Caftan;

public class CaftanDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/caftansshop?user=root&password=";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_CAFTAN_SQL = "INSERT INTO caftan (item_type, description, price, characteristics, availability, image) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CAFTAN_BY_ID = "SELECT * FROM caftan WHERE item_id=?";
    private static final String SELECT_ALL_CAFTAN = "SELECT * FROM caftan";
    private static final String DELETE_CAFTAN_SQL = "DELETE FROM caftan WHERE item_id=?";
    private static final String UPDATE_CAFTAN_SQL = "UPDATE caftan SET item_type=?, description=?, price=?, characteristics=?, availability=?, image=? WHERE item_id=?";

    public CaftanDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertCaftan(Caftan caftan) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAFTAN_SQL)) {
            preparedStatement.setString(1, caftan.getItemType());
            preparedStatement.setString(2, caftan.getDescription());
            preparedStatement.setBigDecimal(3, caftan.getPrice());
            preparedStatement.setString(4, caftan.getCharacteristics());
            preparedStatement.setString(5, caftan.getAvailability());
            preparedStatement.setString(6, caftan.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Caftan selectCaftan(int itemId) {
        Caftan caftan = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAFTAN_BY_ID)) {
            preparedStatement.setInt(1, itemId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String itemType = rs.getString("item_type");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                String characteristics = rs.getString("characteristics");
                String availability = rs.getString("availability");
                byte[] image = rs.getBytes("image");
                caftan = new Caftan(itemId, itemType, description, price, characteristics, availability, Base64.getEncoder().encodeToString(image));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return caftan;
    }

    public List<Caftan> selectAllCaftans() {
        List<Caftan> caftans = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CAFTAN)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                String itemType = rs.getString("item_type");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                String characteristics = rs.getString("characteristics");
                String availability = rs.getString("availability");
                byte[] image = rs.getBytes("image");
                caftans.add(new Caftan(itemId, itemType, description, price, characteristics, availability, Base64.getEncoder().encodeToString(image)));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return caftans;
    }

    public boolean deleteCaftan(int itemId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CAFTAN_SQL)) {
            statement.setInt(1, itemId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCaftan(Caftan caftan) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CAFTAN_SQL)) {
            statement.setString(1, caftan.getItemType());
            statement.setString(2, caftan.getDescription());
            statement.setBigDecimal(3, caftan.getPrice());
            statement.setString(4, caftan.getCharacteristics());
            statement.setString(5, caftan.getAvailability());
            statement.setString(6, caftan.getImage());
            statement.setInt(7, caftan.getItemId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
