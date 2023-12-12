package com.example.allin;

import java.util.List;

public class Admin extends Person {



    public void addItem(int itemId, String itemName, String description, double price, int stockQuantity, Sale sale, String CatName, DbHelper dbHelper, List<byte[]> images) {

        //instance of System
        OnlineShoppingSystem system=new OnlineShoppingSystem();

        //getting category object corresponding to its name
        Category category= system.getCategoryIdByName(CatName);

        //setting item info
        Item newItem = new Item(itemId, itemName, description, price, stockQuantity,category,images);
        newItem.setSale(sale);

        //---> Add item to the database
        long ItemID=dbHelper.insertNewItem(newItem);

        //---> Add item to items list
        newItem.setItemId((int)ItemID);
        system.getItemsList().add(newItem);


    }

    public void AddSale(){}
    public void DeleteItem(){}
    public void AddCategory(){}
    public void DeleteCategory(){}
    public void ShowOrdersInPeriod(){}


    @Override
    public boolean login(String username, String password) {

        return true;
    }
}
