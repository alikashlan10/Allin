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
public class User extends Person{

    private String fullName;
    private String SSN;
    private String Email;
    private int AddressID;
    private String creditCard;
    private UserAddress userAddress;
    private OnlineShoppingSystem system;





    //login function
    @Override
    public boolean login(String enteredUsername, String enteredPassword) {
        List<User> userList = system.users;

        for (User user : userList) {
            if (user!=null && user.getUserName().equals(enteredUsername) && user.getPassword().equals(enteredPassword)) {
                // Login successful
                this.loadUserData(user);
                return true;
            }
        }

        // Login failed
        return false;
    }


    // helper function to set all users data
    private void loadUserData(User user) {
        // Load user data into the current instance
        this.UserName = user.getUserName();
        this.SSN = user.getSSN();
        this.fullName=user.getFullName();
        this.userAddress=user.getUserAddress();
        this.creditCard=user.getCreditCard();
        this.Email=user.getEmail();
        this.PersonID = user.getPersonID();
        // Avoid storing the password in the instance variables
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

    //register function that takes user info and return userID after creation
    public int Register(String username,String pass,String name,String email,String creditCardNum,String ssn,String country
                       ,String City,String street,String buildingNum,String flatNum,DbHelper dbHelper)
    {
        //Building UserAddress object
        UserAddress address=new UserAddress();
        address.setCountry(country);
        address.setCity(City);
        address.setStreet(street);
        address.setBuildingNum(buildingNum);
        address.setFlatNum(flatNum);

        //Building User object
        User user=new User();
        user.setUserName(username);
        user.setPassword(pass);
        user.setSSN(ssn);
        user.setFullName(name);
        user.setEmail(email);
        user.setCreditCard(creditCardNum);
        user.setUserAddress(address);

        //insert user in Users list
        OnlineShoppingSystem system=new OnlineShoppingSystem();
        system.AddUserToTheList(user);

        //insert user in database
        int ID=(int)dbHelper.InsertNewUser(user);

        //return the user id
        return ID;

    }

    public void AddItemsToCart(){}

    public void CancelItem(){}

    public void PlaceOrder(){}

    public void CancelOrder(){}

    public void UpdatePersonalInfo(){}

    public void AddFeedBack(){}



}

