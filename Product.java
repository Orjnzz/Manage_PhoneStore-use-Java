package com.example.javafx;

public class Product {
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Product(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    // You can also add further methods for manipulation if needed

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
