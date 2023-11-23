package org.example;

import org.example.databases.ProductDatabase;
import org.example.databases.UserDatabase;


public class Main {
    public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase();
                MenuNavigation menuNavigation;

        MenuNavigation.mainMenu(userDatabase);

    }
}