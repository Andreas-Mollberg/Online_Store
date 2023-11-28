package org.example.databases;

import org.example.Product;

import java.sql.*;


public class ProductDatabase {
    private static final String URL = "jdbc:sqlite:products.db";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY, name TEXT NOT NULL, price REAL NOT NULL)";

    private static final String INSERT_SQL = "INSERT INTO products (name, price) VALUES (?, ?)";

    private static final String UPDATE_SQL = "UPDATE products SET name = ?, price = ? WHERE id = ?";

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
            statement.setInt(3, product.getId());

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

    public Product getProductById(int productId) {
        String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM products WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL)) {
            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

