package org.example.databases;

import org.example.User;

import java.sql.*;

public class UserDatabase {
    private static final String URL = "jdbc:sqlite:users.db";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username TEXT NOT NULL, password TEXT NOT NULL)";
    private static final String INSERT_SQL = "INSERT INTO users (username, password) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE users SET username = ?, password = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_USERNAME_SQL = "SELECT * FROM users WHERE username = ?";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD_SQL = "SELECT * FROM users WHERE username = ? AND password = ?";
    private static final String SELECT_CURRENT_USER_SQL = "SELECT * FROM users WHERE id = ?";


    public UserDatabase() {
        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(true);
            connection.createStatement().execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByUsername(String username) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME_SQL)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD_SQL)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void updateUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getCurrentUser(int userId) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(SELECT_CURRENT_USER_SQL)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
