package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDatabase {
    private static final String URL = "jdbc:sqlite:orders.db";
    private static final String SELECT_ORDERS_BY_USER_ID_SQL =
            "SELECT * FROM orders WHERE userId = ?";
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS orders " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " userId INTEGER NOT NULL, " +
                    " total REAL NOT NULL)";

    private static final String INSERT_SQL =
            "INSERT INTO orders " +
                    "(userId, total) VALUES (?, ?)";

    public OrderDatabase() {
        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(true);
            connection.createStatement().execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertOrder(int userId, double total) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userId);
            statement.setDouble(2, total);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    System.out.println("Order with ID " + orderId + " added to the database.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getOrdersByUserId(int id) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID_SQL)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("id");
                    double total = resultSet.getDouble("total");
                    // You may also retrieve other order details from the result set

                    // Assuming you have a method to retrieve order items from the database
                    List<Product> orderItems = getOrderItemsByOrderId(orderId);

                    // Create Order object and add to the list
                    Order order = new Order(userId, orderItems, total);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    // Implement a method to retrieve order items based on orderId
    private List<Product> getOrderItemsByOrderId(int orderId) {
        // You need to implement this method based on your database schema
        // Retrieve order items associated with the given orderId from the database
        // Return a list of Product objects
        // ...

        return new ArrayList<>(); // Placeholder, replace with actual implementation
    }
}
