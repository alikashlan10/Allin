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
public class User extends Person{

    private String fullName;
    private String SSN;
    private String Email;
    private int AddressID;
    private String creditCard;
    private UserAddress userAddress;



    @Override
    public int login(String username, String password) {

        //instance of the DbHelper
        //use loginUser function form DbHelper to return user ID if found
        return 0;
    }



    ////////////////////////// Setters and Getters //////////////////////////
    //----------------------------------------------------------------------

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSSN() {
        return SSN;
    }
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public int getAddressID() {
        return AddressID;
    }
    public void setAddressID(int addressID) {
        AddressID = addressID;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public String getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    //----------------------------------------------------------------------
    ////////////////////////////////////////////////////////////////////////



    public void Register(){}

    public void AddItemsToCart(){}

    public void CancelItem(){}

    public void PlaceOrder(){}

    public void CancelOrder(){}

    public void UpdatePersonalInfo(){}

    public void AddFeedBack(){}



}

