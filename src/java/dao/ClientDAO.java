/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Client;

/**
 *
 * @author TechAima
 */
public class ClientDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/caftansshop?user=root&password=";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_Client_SQL = "INSERT INTO `user_account`(`cin`, `username`, `email`, `location_details`, `phone`) VALUES (?, ?, ?, ?,?);";
    private static final String SELECT_Client_BY_ID = "SELECT `user_id`, `cin`, `username`, `email`, `location_details`, `phone`, `registration_time` FROM `user_account` where user_id =?;";
    private static final String SELECT_ALL_Client = "SELECT * FROM user_account;";
    private static final String DELETE_Client_SQL = "DELETE FROM `user_account` WHERE `user_id` = ?;";
    private static final String UPDATE_Client_SQL = "UPDATE user_account SET cin = ?,username = ?,email = ?,location_details = ?, phone =? WHERE  user_id = ?;";

    public ClientDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public Client selectClient(int id) {
        Client client = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Client_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String cin = rs.getString("cin");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String location = rs.getString("location_details");
                String phone = rs.getString("phone");
                Timestamp time = rs.getTimestamp("registration_time");
                client = new Client(id, cin, username, email, location, phone, time);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return client;
    }

    public List<Client> selectAllClient() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Client> client = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Client);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String cin = rs.getString("cin");
                String name = rs.getString("username");
                String email = rs.getString("email");
                String location = rs.getString("location_details");
                String phone = rs.getString("phone");
                Timestamp time = rs.getTimestamp("registration_time");
                client.add(new Client(id, cin, name, email, location, phone, time));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return client;
    }

    public void insertClient(Client client) throws SQLException {
        System.out.println(INSERT_Client_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Client_SQL)) {
            preparedStatement.setString(1, client.getCin());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getLocation());
            preparedStatement.setString(5, client.getPhone());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public boolean updateClient(Client client) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_Client_SQL);) {
            statement.setString(1, client.getCin());
            statement.setString(2, client.getName());
            //statement.setDate(3, new java.sql.Date(client.getStart().getTime()));
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getLocation());
            statement.setString(5, client.getPhone());
            statement.setInt(6, client.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteClient(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_Client_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
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
