/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.allin;
import java.util.List;
/**
 *
 * @author Ali
 */

public class OnlineShoppingSystem {
    private List<Category> categories;
    private List<User> users;
    private List<Order> orders;

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        // Add category implementation
    }

    public void deleteCategory(Category category) {
        // Delete category implementation
    }

    public void addUser(User user) {
        // Add user implementation
    }

    public void deleteUser(User user) {
        // Delete user implementation
    }

    public Order placeOrder(User user, List<Item> items) {
        // Place order implementation
        return null;
    }

    public void cancelOrder(Order order) {
        // Cancel order implementation
    }

    public double calculateTotalReceipt(Order order) {
        // Calculate total receipt implementation
        return 0.0;
    }

    public void notifyUsers(Order order) {
        // Notify users implementation
    }

    public void applySale(Item item, Sale sale) {
        // Apply sale implementation
    }
}

