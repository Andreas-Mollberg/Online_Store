package org.example.databases;

import org.example.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProductDatabase {
    private static final String URL = "jdbc:sqlite:products.db";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY, name TEXT NOT NULL, price REAL NOT NULL, quantity INTEGER NOT NULL)";
    private static final String INSERT_SQL = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM products WHERE id = ?";

    public ProductDatabase() {
        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(true);
            connection.createStatement().execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setInt(4, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

