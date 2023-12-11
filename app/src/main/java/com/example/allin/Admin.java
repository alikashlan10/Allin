package com.example.allin;

public class Admin extends Person {



    public void AddItem(){}
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
