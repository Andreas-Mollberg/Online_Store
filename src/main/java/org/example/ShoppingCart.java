package org.example;

import java.util.ArrayList;
import java.util.List;
public class ShoppingCart {

    private List<Product> productsInCart;
    private boolean checkedOut;

    public ShoppingCart() {
        this.productsInCart = new ArrayList<>();
        this.checkedOut = false;
    }

    public void addProduct(Product product) {
        if(!checkedOut) {
            productsInCart.add(product);
            System.out.println(product.getName() + " added to cart.");
        }else{
            System.out.println("Cannot add product to cart. Cart is already checked out.");
        }
    }

    public void viewCart(){
        if(productsInCart.size() > 0) {
            System.out.println("Products in cart: ");
            for (Product product : productsInCart) {
                System.out.println(product.getName() + " " + product.getPrice());
            }
            System.out.println("Total price: " + getTotalPrice());
        }else{
            System.out.println("Cart is empty.");
        }
    }

    public void checkout(){
        if (!productsInCart.isEmpty() && !checkedOut){
            System.out.println("Sending order to warehouse...");

            Order order = new Order(productsInCart, getTotalPrice());
            order.displayOrderDetails();

            checkedOut = true;
            productsInCart.clear();
        }else{
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

}
