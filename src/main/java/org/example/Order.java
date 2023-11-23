package org.example;

import java.util.List;

public class Order {

        private int id;
        private int userId;
        private int productId;
        private int quantity;
        private List<Product> orderItems;
        private double totalPrice;


        public Order(List<Product> orderItems, double totalPrice) {
            this.orderItems = orderItems;
            this.totalPrice = totalPrice;
        }

        public void displayOrderDetails(){
            System.out.println("Order details: ");
            for (Product product : orderItems) {
                System.out.println(product.getName() + " " + product.getPrice());
            }
            System.out.println("Total price: " + totalPrice);
        }

        public String getOrderDetails(){
            String orderDetails = "";
            for (Product product : orderItems) {
                orderDetails += product.getName() + " " + product.getPrice() + "\n";
            }
            orderDetails += "Total price: " + totalPrice;
            return orderDetails;
        }
}
