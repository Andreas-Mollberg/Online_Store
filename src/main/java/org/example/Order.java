package org.example;

import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private int userId;
    private List<Product> orderItems; // Use Product directly
    private double totalPrice;
    private Date orderDate;

    public Order(int userId, List<Product> orderItems, double totalPrice) {
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.orderDate = new Date();
    }

    public void displayOrderDetails() {
        System.out.println("Order details: ");
        for (Product product : orderItems) {
            System.out.println(product.getName() + " " + product.getPrice());
        }
        System.out.println("Total price: " + totalPrice);
    }

    public String getOrderDetails() {
        String orderDetails = "";
        for (Product product : orderItems) {
            orderDetails += product.getName() + " " + product.getPrice() + "\n";
        }
        orderDetails += "Total price: " + totalPrice;
        return orderDetails;
    }

    public int getUserId() {
        return userId;
    }

    public String getOrderDate() {
        return orderDate.toString();
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
