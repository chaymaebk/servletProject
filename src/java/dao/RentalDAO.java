package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Rental;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 *
 * @author Ramesh Fadatare
 *
 */
public class RentalDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/caftansshop?user=root&password=";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_Rental_SQL = "INSERT INTO rental (item_id, user_id, start_date, end_date) VALUES (?, ?, ?, ?);";
    private static final String SELECT_Rental_BY_ID = "select `rental_id`, `item_id`, `user_id`, `start_date`, `end_date`, `somme` from rental where rental_id =?;";
    private static final String SELECT_ALL_Rental = "SELECT * FROM `rental`;";
    private static final String DELETE_Rental_SQL = "DELETE FROM `rental` WHERE `rental_id` = ?;";
    private static final String UPDATE_Rental_SQL = "UPDATE rental SET item_id = ?,user_id = ?,start_date = ?,end_date = ?, somme = (DATEDIFF(?, ?) * (SELECT price FROM caftan WHERE item_id = ?)) WHERE  rental_id = ?;";

    public RentalDAO() {
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

    public void insertRental(Rental rental) throws SQLException {
        System.out.println(INSERT_Rental_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Rental_SQL)) {
            preparedStatement.setInt(1, rental.getItem());
            preparedStatement.setInt(2, rental.getUser());
            // Supposons que start et end sont des objets java.util.Date
            java.util.Date startUtil = rental.getStart();
            java.util.Date endUtil = rental.getEnd();

// Convertir java.util.Date en java.sql.Date
            java.sql.Date startSql = new java.sql.Date(startUtil.getTime());
            java.sql.Date endSql = new java.sql.Date(endUtil.getTime());

// Utiliser startSql et endSql dans votre PreparedStatement
            preparedStatement.setDate(3, startSql);
            preparedStatement.setDate(4, endSql);

            //preparedStatement.setDate(3,(java.sql.Date) rental.getStart());
            //preparedStatement.setDate(4,(java.sql.Date) rental.getEnd());
            //preparedStatement.setInt(5, rental.getSomme());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Rental selectRental(int id) {
        Rental rental = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Rental_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int item = rs.getInt("item_id");
                int user = rs.getInt("user_id");
                Date start = rs.getDate("start_date");
                Date end = rs.getDate("end_date");
                int somme = rs.getInt("somme");
                rental = new Rental(id, item, user, start, end, somme);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rental;
    }

    public List<Rental> selectAllRental() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Rental> rental = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Rental);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("rental_id");
                int item = rs.getInt("item_id");
                int user = rs.getInt("user_id");
                Date start = rs.getDate("start_date");
                Date end = rs.getDate("end_date");
                int somme = rs.getInt("somme");
                rental.add(new Rental(id, item, user, start, end, somme));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rental;
    }

    public boolean deleteRental(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_Rental_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateRental(Rental rental) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_Rental_SQL);) {
            statement.setInt(1, rental.getItem());
            statement.setInt(2, rental.getUser());
            statement.setDate(3, new java.sql.Date(rental.getStart().getTime()));
            statement.setDate(4, new java.sql.Date(rental.getEnd().getTime()));
            statement.setDate(5, new java.sql.Date(rental.getEnd().getTime()));
            statement.setDate(6, new java.sql.Date(rental.getStart().getTime()));
            statement.setInt(7, rental.getItem());
            //statement.setInt(5, rental.getSomme());
            statement.setInt(8, rental.getId());
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
