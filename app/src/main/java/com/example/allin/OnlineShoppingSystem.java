/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.allin;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ali
 */
public class OnlineShoppingSystem {
    private List<Category> categories = new ArrayList<>();
    public  List<User> users = new ArrayList<>();
    public List<Admin> admins = new ArrayList<>();
    private List<Order> orders  = new ArrayList<>();
    private List<Item> items  = new ArrayList<>();
    private List<Feedback> feedbacks= new ArrayList<>();
    private Person currentPerson;
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
        List<Admin> adminFromDatabase = dbHelper.getAllAdmins();
        // Clear the existing list and add users from the database
        users.clear();
        users.addAll(usersFromDatabase);
        admins.clear();
        admins.addAll(adminFromDatabase);
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
    public Person getCurrentPerson() {
        return currentPerson;
    }
    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }
    //adding user
    public void addUser(User user) {users.add(user);}
    //deleting user
    public void deleteUser(User user) {
        if (!users.isEmpty()) {
            users.remove(user);
        }
    }
    //w da apply sale
    public void applySale(Item item, float sale) {
        double price_after_discount = item.getPrice() - item.getPrice()*item.getSale();
        System.out.println("The new price after discount is: "+ price_after_discount);
    }
}

