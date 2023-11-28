package org.example;


import org.example.databases.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> productsInCart;
    private boolean checkedOut;
    private User currentUser;

    public ShoppingCart(User currentUser) {
        this.productsInCart = new ArrayList<>();
        this.checkedOut = false;
        this.currentUser = currentUser;
    }

    public void addProduct(Product product, int quantity) {
        if (!checkedOut) {
            for (int i = 0; i < quantity; i++) {
                productsInCart.add(product);
            }
            System.out.println("Added " + quantity + " " + product.getName() + " to cart.");
        } else {
            System.out.println("Cannot add product. Cart is already checked out.");
        }
    }

    public void viewCart() {
        productsInCart.forEach(System.out::println);

    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public void checkout(OrderDatabase orderdatabase) {
        if (!productsInCart.isEmpty() && !checkedOut) {
            System.out.println("Sending order to database...");

            double totalPrice = getTotalPrice();
            orderdatabase.insertOrder(currentUser.getId(), totalPrice);

            // Additional logic or display the order details if needed

            checkedOut = true;
            productsInCart.clear();
        } else {
            System.out.println("Cannot checkout. Cart is empty or already checked out.");
        }
    }

    private double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : productsInCart) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "productsInCart=" + productsInCart +
                '}';
    }
}
