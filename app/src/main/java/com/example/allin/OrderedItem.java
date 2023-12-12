/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.allin;

/**
 *
 * @author Ali
 */
public class OrderedItem {
    private int orderedItemId;
    private Item item;
    private int quantity;
    private double subtotal;

    public OrderedItem(int orderedItemId, Item item, int quantity) {
        this.orderedItemId = orderedItemId;
        this.item = item;
        this.quantity = quantity;
        calculateSubtotal();
    }

    public int getOrderedItemId() {
        return orderedItemId;
    }

    public void setOrderedItemId(int orderedItemId) {
        this.orderedItemId = orderedItemId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        calculateSubtotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    private void calculateSubtotal() {
        Sale sale = item.getSale();
        double itemPrice = item.getPrice();

        if (sale != null) {
            double discountedPrice = itemPrice - (itemPrice * sale.getDiscountPercentage() / 100);
            this.subtotal = quantity * discountedPrice;
        } else {
            this.subtotal = quantity * itemPrice;
        }
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
        calculateSubtotal();
    }

    public void updateItem(Item newItem) {
        this.item = newItem;
        calculateSubtotal();
    }
}
