package org.example;


import org.example.databases.ProductDatabase;
import org.example.databases.UserDatabase;

public class Main {
    public static void main(String[] args) {

        runDropTables();

        runTestCode();
    }

    private static void runDropTables() {
        DropTable.dropUserOrdersTable();
        DropTable.dropProductsTable();
        DropTable.dropUsersTable();

    }

    private static void runTestCode() {
        UserDatabase userDatabase = new UserDatabase();
        ProductDatabase productDatabase = new ProductDatabase();
        OrderDatabase orderDatabase = new OrderDatabase();;

        User currentUser = userDatabase.getUserByUsername("testUser");

        // Run the test setup
        setupTestData(userDatabase, productDatabase);

        ShoppingCart shoppingCart = new ShoppingCart(currentUser);
        MenuNavigation menuNavigation = new MenuNavigation(shoppingCart, userDatabase, productDatabase);

        menuNavigation.mainMenu(userDatabase);
    }


    private static void setupTestData(UserDatabase userDatabase, ProductDatabase productDatabase) {
        // Create a test user
        User testUser = new User("testUser", "password");
        userDatabase.insertUser(testUser);

        // Add some test products to the product database
        Product product1 = new Product(1, "Product 1", 10.0);
        Product product2 = new Product(2, "Product 2", 15.0);
        Product product3 = new Product(3, "Product 3", 20.0);

        productDatabase.insertProduct(product1);
        productDatabase.insertProduct(product2);
        productDatabase.insertProduct(product3);

        // Create a test order for the user
        ShoppingCart testCart = new ShoppingCart(testUser);
        testCart.addProduct(product1, 2 );
        testCart.addProduct(product2, 3);
        testCart.addProduct(product3, 1);

        // Checkout the test order
        testCart.checkout(new OrderDatabase());
    }
}