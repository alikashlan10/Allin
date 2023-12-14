package com.example.allin;

import java.util.List;

public class Admin extends Person {

    private Admin(int adminID, String username, String password) {
        this.PersonID = adminID;
        this.UserName = username;
        this.password = password;
    }
    public Admin() {
    }

    @Override
    public boolean login(String username, String password, DbHelper db) {
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        int adminID = db.loginAdmin(username, password);
        if(adminID != -1) {
            Admin admin = new Admin(adminID, username, password);
            system.setCurrentPerson(admin);
            return true;
        }
        return false;
    }

    public void addItem(int itemId, String itemName, String description, double price, int stockQuantity, float sale, String CatName, DbHelper dbHelper, List<byte[]> images) {
        //instance of System
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        //getting category object corresponding to its name
        Category category= system.getCategoryIdByName(CatName);
        //setting item info
        Item newItem = new Item(itemId, itemName, description, price, stockQuantity,0,category,images);
        newItem.setSale(sale);
        //---> Add item to the database
        long ItemID=dbHelper.insertNewItem(newItem);
        //---> Add item to items list
        newItem.setItemId((int)ItemID);
        system.getItemsList().add(newItem);
    }
    public void DeleteItem(Item item,DbHelper dbHelper){
        //instance of System
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        //---> delete item from the database
        dbHelper.DeleteItem(item.getItemId());
        //---> delete item from the list
        system.getItemsList().remove(item);
    }

    public void AddCategory(String categoryName,DbHelper dbHelper){
        //instance of System
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        //setting item info
        Category newCategory = new Category(categoryName);
        //---> Add item to the database
        long CategoryID=dbHelper.insertNewCategory(newCategory);
        //---> Add item to items list
        newCategory.setCategoryId((int)CategoryID);
        system.getCategories().add(newCategory);
    }
    public void DeleteCategory(Category category,DbHelper dbHelper){
        //instance of System
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        //---> delete item from the database
        dbHelper.DeleteCategory(category.getCategoryId());
        //---> delete item from the list
        system.getCategories().remove(category);
    }


    public void DeleteUser(User user)
    {
        //Instance of system
        OnlineShoppingSystem system=OnlineShoppingSystem.getInstance();

        //Delete user from user list
        system.users.remove(user);

        //Delete user from database


    }

    public void ShowOrdersInPeriod(){}
    public void AddSale(){

    }
}
