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

    public OrderedItem(int orderedItemId, Item item, int quantity, double subtotal) {
        this.orderedItemId = orderedItemId;
        this.item = item;
        this.quantity = quantity;
        this.subtotal = subtotal;
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
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
