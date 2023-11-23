package org.example;


import org.example.databases.ProductDatabase;
import org.example.databases.UserDatabase;

import java.util.Scanner;


public class MenuNavigation {

    public static void mainMenu(UserDatabase userDatabase) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("1. Login existing user");
            System.out.println("2. Create new user");
            System.out.println("3. Exit program\n");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    loginUser(userDatabase, scanner);
                    break;
                case 2:
                    createUser(userDatabase, scanner);
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

    private static void createUser(UserDatabase userDatabase, Scanner scanner) {
        System.out.println("\nCreate new user\n");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);
        userDatabase.insertUser(user);
        System.out.println("User created successfully!");
    }

    private static void loginUser(UserDatabase userDatabase, Scanner scanner) {
        System.out.println("\nLogin existing user\n");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDatabase.authenticateUser(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }


}

