package com.example.allin;

public class CartItem {

    private int CartItemID;
    private int userID;
    private Item item;
    private int Quantity;



    public int getCartItemID() {
        return CartItemID;
    }
    public void setCartItemID(int cartItemID) {
        CartItemID = cartItemID;
    }


    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }


    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }


    public int getQuantity() {
        return Quantity;
    }
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }


    // a function to calculate subtotal to the Cart Item
    public double CalculateSubTotal()
    {
        return (this.Quantity * this.item.getPrice());
    }

}
