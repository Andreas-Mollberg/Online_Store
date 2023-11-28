package org.example;

import java.sql.*;

public class OrderDatabase {
    private static final String URL = "jdbc:sqlite:orders.db";
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
}
