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

// el helly belly eshta8l
// bta3t ali sayed
public class Item {
    private int itemId;
    private String itemName;
    private String description;
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private Category category;
    private float sale;
    private List<byte[]> images;

    public Item(int itemId, String itemName, String description, double price, int stockQuantity,int soldQuantity,Category category,List<byte[]> images) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.soldQuantity=soldQuantity;
        this.category=category;
        this.images= images;
    }

    public List<byte[]> getImages() {
        return images;
    }
    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public double setPrice(double price) {
        this.price = price;
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public float getSale() {
        return sale;
    }
    public void setSale(float sale) {
        this.sale = sale;
    }


    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }
    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

}

