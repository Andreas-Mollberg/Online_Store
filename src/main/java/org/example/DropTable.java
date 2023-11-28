package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTable {
    private static final String USER_URL = "jdbc:sqlite:users.db";
    private static final String PRODUCT_URL = "jdbc:sqlite:products.db";
    private static final String USER_DROP_TABLE_SQL = "DROP TABLE IF EXISTS users";
    private static final String PRODUCT_DROP_TABLE_SQL = "DROP TABLE IF EXISTS products";

    public static void dropUsersTable() {
        dropTable(USER_URL, USER_DROP_TABLE_SQL, "users");
    }

    public static void dropProductsTable() {
        dropTable(PRODUCT_URL, PRODUCT_DROP_TABLE_SQL, "products");
    }

    public static void dropUserOrdersTable(){
        dropTable(USER_URL, "DROP TABLE IF EXISTS userOrders", "userOrders");
    }


    private static void dropTable(String url, String dropTableSQL, String tableName) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            // Execute the DROP TABLE statement
            statement.execute(dropTableSQL);

            System.out.println("Table " + tableName + " dropped successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
