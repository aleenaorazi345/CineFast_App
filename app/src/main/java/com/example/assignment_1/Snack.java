package com.example.assignment_1;
public class Snack {
    private String name;
    private String description;
    private double price;
    private int imageResId;
    private int quantity;

    public Snack(String name, String description, double price, int imageResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = 0; // Default quantity
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}