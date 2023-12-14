/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.allin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ali
 */
public class Order {
    private int orderId;
    private int userID;
    private List<CartItem> items = new ArrayList<>();
    private String orderDate;
    private String status;
    private String deliveryDate;
    private double totalAmount;

    public Order(int orderId, int user, List<CartItem> items, String orderDate, String status,
                 String deliveryDate, double totalAmount) {
        this.orderId = orderId;
        this.userID = user;
        this.items = items;
        this.status=status;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.totalAmount = totalAmount;
    }

    public Order(){}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int user) {
        this.userID = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}


