/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.allin;


import android.content.ContentValues;

import java.util.ArrayList;
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
    private List<CartItem> Cart  = new ArrayList<>();



    //empty constructor
    public List<CartItem> getCart() {
        return Cart;
    }





    //login function depends on user List in the System class
    @Override
    public boolean login(String enteredUsername, String enteredPassword,DbHelper db) {
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        List<User> userList = system.users;
        for (User user : userList) {
            if (user!=null && user.getUserName().equals(enteredUsername) && user.getPassword().equals(enteredPassword)) {
                // Login successful
                this.loadUserData(user);
                system.setCurrentPerson(user);
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
    public int Register(String username,String pass,String name,String email,String ssn,String country
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
        //user.setCreditCard(creditCardNum);
        user.setUserAddress(address);


        //---> insert user in database
        int userID=(int)dbHelper.InsertNewUser(user);


        //set User ID
        user.setPersonID(userID);

        //---> insert user in Users list
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        system.AddUserToTheList(user);


        //return the user id
        return userID;

    }


    // AddItemsToCart
    public int AddItemsToCart(Item item,int quantity,int userID,DbHelper dbHelper)
    {
        //int variable to save ID
        int CartItemID;

        //instance of Cart item
        CartItem cartItem=new CartItem();

        //set Cart info
        cartItem.setItem(item);
        cartItem.setUserID(userID);
        cartItem.setQuantity(quantity);

        //---> add item to database
        CartItemID =(int)dbHelper.InsertNewCartItem(cartItem);

        //set cart Item iD
        cartItem.setCartItemID(CartItemID);

        //---> add the item to the Cart list
        Cart.add(cartItem);


        return CartItemID;
    }
    // Edit item quantity in cart
    public void EditCartItem(int CartItemID,int newQuantity,DbHelper dbHelper)
    {
        //Update the item quantity in the Cart list
        for (CartItem cartItem : Cart) {
            if (cartItem.getCartItemID() == CartItemID) {

                // Update the quantity
                cartItem.setQuantity(newQuantity);

                break;
            }
        }

        //Update the item quantity in the database
        dbHelper.UpdateItemQuantityInCart(CartItemID,newQuantity);

    }
    // Cancel item from Cart
    public void CancelItem(int CartItemID,DbHelper dbHelper)
    {
        // Remove item from cart list
        for (CartItem cartItem : Cart) {
            if (cartItem.getCartItemID() == CartItemID) {

                // Update the quantity
                Cart.remove(cartItem);

                break;
            }
        }

        // Remove CartItem from database
        dbHelper.DeleteCartItem(CartItemID);

    }


    // Add feedback
    public void AddFeedBack(User user,Item item,String comment,int rating,DbHelper dbhelper)
    {
        OnlineShoppingSystem system=OnlineShoppingSystem.getInstance();

        int FeedbackID;

        //Add feedback to database
        FeedbackID=(int)dbhelper.insertNewFeedback(comment,rating,user.getPersonID(), item.getItemId());

        //Add Feedback to feedback list
        system.getFeedbacks().add(new Feedback(FeedbackID,user.getPersonID(),item.getItemId(),comment,rating));

        //system.getFeedbacks().add(new Feedback())

    }

    // Cancel Order
    public void CancelOrder(int orderID,DbHelper dbHelper)
    {
        OnlineShoppingSystem system=OnlineShoppingSystem.getInstance();

        //Update Status in Order list
        for (Order order:system.getOrders()) {
            if (order.getOrderId()==orderID){
                order.setStatus("Canceled");
                break;
            }

        }

        //Update status in database
        dbHelper.cancelOrder(orderID);

    }


    // Get the user Orders
    public List<Order> GetMyOrders()
    {
        OnlineShoppingSystem sys=OnlineShoppingSystem.getInstance();
        List<Order> myOrders=new ArrayList<>();

        for (Order order: sys.getOrders()) {
            if(order.getUserID() == this.getPersonID())
                myOrders.add(order);

        }

        return myOrders;
    }


    public void UpdatePersonalInfo(String newName, String newEmail, String newCity,
                                   String newStreet, String newBuildingNum, String newFlatNum, DbHelper dbHelper) {
        //set New Info
        this.fullName = newName;
        this.Email = newEmail;
        this.userAddress.setCity(newCity);
        this.userAddress.setStreet(newStreet);
        this.userAddress.setBuildingNum(newBuildingNum);
        this.userAddress.setFlatNum(newFlatNum);

        //ContentValues
        ContentValues values = new ContentValues();
        values.put("FullName", newName);
        values.put("Email", newEmail);
        values.put("City", newCity);
        values.put("Street", newStreet);
        values.put("BuildingNum", newBuildingNum);
        values.put("FlatNum", newFlatNum);

        //Update DB
        dbHelper.getWritableDatabase().update("User", values, "UserID = ?",
                new String[]{String.valueOf(this.getPersonID())});
    }




}

