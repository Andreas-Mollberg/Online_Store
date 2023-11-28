package org.example;


import org.example.databases.ProductDatabase;
import org.example.databases.UserDatabase;

import java.util.Scanner;


public class MenuNavigation {

    private ShoppingCart shoppingCart;
    private UserDatabase userDatabase;
    private ProductDatabase productDatabase;


    public MenuNavigation(ShoppingCart shoppingCart, UserDatabase userDatabase, ProductDatabase productDatabase) {
        this.shoppingCart = shoppingCart;
        this.userDatabase = userDatabase;
        this.productDatabase = productDatabase;

    }

    public void mainMenu(UserDatabase userDatabase) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("1. Login existing user");
            System.out.println("2. Create new user");
            System.out.println("3. Exit program\n");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loginUser(scanner);
                    break;
                case 2:
                    createUser(scanner);
                    break;
                case 3:
                    System.out.println("Closing program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createUser(Scanner scanner) {
        System.out.println("\nCreate new user\n");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);
        userDatabase.insertUser(user);
        System.out.println("User created successfully!");
    }

    private void loginUser(Scanner scanner) {
        System.out.println("\nLogin existing user\n");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDatabase.authenticateUser(username, password)) {
            // Retrieve the current user from the database using the username
            User currentUser = userDatabase.getUserByUsername(username);

            // Update the shopping cart with the current user
            shoppingCart.setCurrentUser(currentUser);

            // Call the shopping menu with the updated shopping cart
            shoppingMenu(scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void shoppingMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Add product to cart");
            System.out.println("2. View cart");
            System.out.println("3. Checkout");
            System.out.println("4. Logout\n");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProductToCart(scanner);
                    break;
                case 2:
                    shoppingCart.viewCart();
                    break;
                case 3:
                    shoppingCart.checkout(new OrderDatabase());
                    break;
                case 4:
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addProductToCart(Scanner scanner) {
        System.out.println("\nAdd product to cart\n");
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Product product = productDatabase.getProductById(productId);

        if (product != null) {
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            shoppingCart.addProduct(product, quantity);
        } else {
            System.out.println("Product not found. Please enter a valid product ID.");
        }
    }
}




