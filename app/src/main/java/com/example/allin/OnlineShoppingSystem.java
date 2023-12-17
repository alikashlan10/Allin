/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.allin;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private List<Item> bestSellers =new ArrayList<>();
    private Person CurrentPerson;
    private static OnlineShoppingSystem instance = new OnlineShoppingSystem();
    private OnlineShoppingSystem(){}

    //private List<String> categoriesnames;

    String shit;
    public  List<String> getCateogriesNames()
    {
        List<String> categoriesnames=new ArrayList<>();
        for (Category cat:categories) {
            categoriesnames.add(cat.getCategoryName());
        }
        return categoriesnames;
    }


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


    // ------------- Load Lists
    ////////////////////////////////////////////////////////////////////////
    //load all categories from database to the categories list
    public void loadCategoriesDatabase(DbHelper dbHelper) {
        // Call the getAllUsers method from DbHelper to get all users from the database
        List<Category> CategoriesFromDatabase = dbHelper.getAllCategories();

        // Clear the existing list and add users from the database
        categories.clear();
        categories.addAll(CategoriesFromDatabase);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    //load all users from database into the Users list
    public void loadUsersFromDatabase(DbHelper dbHelper) {

        // Call the getAllUsers method from DbHelper to get all users from the database
        List<User> usersFromDatabase = dbHelper.getAllUsers();
        // Clear the existing list and add users from the database
        //users.clear();
        users.addAll(usersFromDatabase);
    }


    //load all orders from database into the Orders list
    public void loadOrdersFromDatabase(DbHelper dbHelper)
    {
        List<Order> ordersFromDatabase = dbHelper.getAllOrders();

        orders.clear();
        orders.addAll(ordersFromDatabase);
    }

    //laod all items from database into the items list
    public void loadItemsFromDatabase(DbHelper dbHelper)
    {
        List<Item> ItemsFromDatabase = dbHelper.getAllItems();

        items.clear();
        items.addAll(ItemsFromDatabase);
    }
    ////////////////////////////////////////////////////////////////////////



    // ------------- Initialize App data
    ////////////////////////////////////////////////////////////////////////
    public void InitializeAppData(DbHelper dbHelper)
    {
        loadUsersFromDatabase(dbHelper);
        loadCategoriesDatabase(dbHelper);
        loadItemsFromDatabase(dbHelper);
        loadOrdersFromDatabase(dbHelper);
    }
    ////////////////////////////////////////////////////////////////////////



    // ------------- Category helpers
    ////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////



    // ------------- setters and getters
    ////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////



    // ------------- Custom functions
    ////////////////////////////////////////////////////////////////////////
    //Place an Order
    public void placeOrder(User user, DbHelper dbHelper) {

        List<CartItem> userCart = user.getCart();

        long OrderID;

        //Add order in database
        OrderID = dbHelper.InsertNewOrder(user.getPersonID(),getDate(),calculateTotalAmount(user.getCart()),getDate(),"in progress",user.getCart());
        //Create object of Order
        Order order=new Order((int)OrderID,user.getPersonID(),user.getCart(),getDate(),"in progress",getDate(),calculateTotalAmount(user.getCart()));
        //Add to sys
        orders.add(order);


        //Update sold and stock quantities in items list and database
        for (CartItem item  :user.getCart()) {

                UpdateItemQuantities(item.getItem().getItemId(), item.getQuantity());
                dbHelper.updateItemQuantities(item.getItem().getItemId(), item.getQuantity());

        }


        //Remove cart
        //Delete cart items from database
        DeleteCartFromDatabase(user.getCart(),dbHelper);
        //Clear cart
        user.getCart().clear();
    }

    //Delete cart from database
    public void DeleteCartFromDatabase(List<CartItem> cartitems,DbHelper dbHelper)
    {
        for (CartItem item : cartitems) {
            dbHelper.DeleteCartItem(item.getCartItemID());
        }

    }

    //Set bestSellers
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void setTopSoldItems(List<Item> itemList, List<Item> bestSellers, int topN) {
        // Sort the list based on SoldQuantity in descending order
        Collections.sort(itemList, Comparator.comparingInt(Item::getSoldQuantity).reversed());

        // Set the top N items into the target list
        bestSellers.clear();
        bestSellers.addAll(itemList.subList(0, Math.min(topN, itemList.size())));
    }

    //Update item (helper function to update all items quantities according to the Order)
    public void UpdateItemQuantities(int itemID,int quantity)
    {
        for (Item item: items) {

            if(item.getItemId()==itemID)
            {
                item.setSoldQuantity(item.getSoldQuantity()+quantity);
                item.setStockQuantity(item.getStockQuantity()-quantity);
            }

        }
    }

    //Get Items by specific Category
    public List<Item> getItemByCategory(String CategoryName)
    {
        List<Item> CatItems =new ArrayList<>();

        for (Item item:items) {

        if (item.getCategory().getCategoryName().equals(CategoryName))
            CatItems.add(item);
        }
        return CatItems;
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

    public void UpdatePassword(String username,String newpass,DbHelper dbHelper)
    {
        for (User user:users) {
            if(user.getUserName().equals(username))
                user.setPassword(newpass);

        }

        dbHelper.UpdatePassword(username,newpass);


    }


    public List<Item> SearchByText(String label)
    {
        List<Item> Searchitems=new ArrayList<>();
        for (Item item:items) {
            if(item.getItemName().equals(label))
                Searchitems.add(item);

        }
        return Searchitems;
    }

    ////////////////////////////////////////////////////////////////////////

}

