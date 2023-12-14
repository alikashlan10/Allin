/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.allin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
/**
 *
 * @author Ali
 */
public class OnlineShoppingSystem {


    private List<Category> categories = new ArrayList<>();
    public  List<User> users = new ArrayList<>();
    public  List<Admin> admins = new ArrayList<>();
    private List<Order> orders  = new ArrayList<>();
    private List<Item> items  = new ArrayList<>();
    private List<Feedback> feedbacks= new ArrayList<>();
    private Person CurrentPerson;
    private static OnlineShoppingSystem instance = new OnlineShoppingSystem();
    private OnlineShoppingSystem(){}

    //Singelton pattern for instances creation
    public static OnlineShoppingSystem getInstance(){
        if(instance ==null){
            instance=new OnlineShoppingSystem();
        }
        return instance;

    }


    //helper function to add a new user to the usersList
    public void AddUserToTheList(User user)
    {
        users.add(user);
    }


    //load all categories from database to the categories list
    public void loadCategoriesDatabase(DbHelper dbHelper) {
        // Call the getAllUsers method from DbHelper to get all users from the database
        List<Category> CategoriesFromDatabase = dbHelper.getAllCategories();

        // Clear the existing list and add users from the database
        categories.clear();
        categories.addAll(CategoriesFromDatabase);
    }


    //load all users from database into the Users list
    public void loadUsersFromDatabase(DbHelper dbHelper) {

        // Call the getAllUsers method from DbHelper to get all users from the database
        List<User> usersFromDatabase = dbHelper.getAllUsers();
        // Clear the existing list and add users from the database
        users.clear();
        users.addAll(usersFromDatabase);
    }


    //Category helpers
    //------------------------------------------------------------------------------------
    //get categories list
    public List<Category> getCategories() {
        return categories;
    }


    //helper function to add a new category to the categories list
    public void addCategory(Category category) {
        categories.add(category);
    }

    //helper function to get a category object by its ID
    public Category getCategoryIdByName(String categoryName) {

        //search the list
        for (Category category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
        }

        // if not found
        return null;
    }
    //------------------------------------------------------------------------------------



    // get items list
    public List<Item> getItemsList() {
        return items;
    }

    // get and set Feedbacks list
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    // get and set CurrentPerson
    public Person getCurrentPerson() {
        return CurrentPerson;
    }
    public void setCurrentPerson(Person currentPerson) {
        CurrentPerson = currentPerson;
    }


    // Helper function to place an order
    public void placeOrder(User user, DbHelper dbHelper) {

        List<CartItem> userCart = user.getCart();

        long OrderID;

        // Add order in database
        OrderID = dbHelper.InsertNewOrder(user.getPersonID(),getDate(),calculateTotalAmount(user.getCart()),getDate(),"in progress",user.getCart());

        //create object of Order
        Order order=new Order((int)OrderID,user,user.getCart(),getDate(),"in progress",getDate(),calculateTotalAmount(user.getCart()));

        //add to sys
        orders.add(order);

        //Clear cart
        user.getCart().clear();
    }


    //orderId
    private int OrderId() {
        return orders.size() + 1;
    }


    //Total
    private double calculateTotalAmount(List<CartItem> cartItems) {
        // Replace this with your logic to calculate the total amount
        double Total = 0.0;
        for (CartItem cartItem : cartItems) {
            Total += cartItem.CalculateSubTotal();
        }
        return Total;
    }


    //Date
    private String getDate() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(new Date());
    }

}

