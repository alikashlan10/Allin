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

public class OnlineShoppingSystem {
    private List<Category> categories;
    private List<User> users;
    private List<Order> orders;



    //da elsingleton aho
    private static OnlineShoppingSystem instance = new OnlineShoppingSystem();
    private OnlineShoppingSystem(){}
    public static OnlineShoppingSystem getInstance(){
        if(instance ==null){
            instance=new OnlineShoppingSystem();
        }
        return instance;
    }



    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
        System.out.println("Category: "+category+" is Added");
    }


    //In the edit Category you should put the original category and replace it with edited category
    public void EditCategory(Category category, Category new_Ctageory) {
        System.out.println("Category before edit: "+ category);
        category=new_Ctageory;
        System.out.println("Category after edit: "+ category);
    }

    //Deleting category
    public void deleteCategory(Category category) {
        System.out.println("Categories Before Deletion: "+ categories);
        System.out.println("Category deleted is : "+ category);
        if(!categories.isEmpty()){
            categories.remove(category);
        }
        System.out.println("Categories after deletion: "+ categories);

    }

    //adding user
    public void addUser(User user) {

        users.add(user);
        System.out.println("User : " + user +" is added");
    }

    //deleting user
    public void deleteUser(User user) {
        System.out.println("User: " + user + " is deleted");
        if (!users.isEmpty()) {
            users.remove(user);
        }
    }

    // !!!!!    rakezo hna 3shan mmkn akon 3akek     !!!!!!
    public Order placeOrder(User user, List<OrderedItem> items) {
        Order order=null;
        int orderId=order.getOrderId();
        String orderDate= order.getOrderDate();
        String status=order.getStatus();
        String deliveryDate=order.getDeliveryDate();
        double totalAmount=order.getTotalAmount();
        order=new Order(orderId,user, items,orderDate,status,deliveryDate,totalAmount);
       System.out.println("The order you just placed is: ");
       return order;
    }

    // w hna b3ml cancelling ll order 3n taree2 kol haga brg3ha lnull
    public void cancelOrder(Order order) {
        order.setStatus("");
        order.setOrderId(-1);
        order.setOrderDate("");
        order.setItems(null);
        order.setUser(null);
        order.setDeliveryDate("");
        order.setTotalAmount(-1);
        System.out.println("Order is canceled");

    }


    // hna ghayart el parameter hwa kan object mn Order
    //3shan Class order mfihosh quantity wla price
    public double calculateTotalReceipt(OrderedItem orderedItem,Item item) {

        int quantity=orderedItem.getQuantity();
        double price=item.getPrice();

        return quantity*price;
    }

    //w da notification msh 3arf 22ol eh tany
    public void notifyUsers(Order order) {
        System.out.println("Order Notification ");
        System.out.println("Order Details:  ");
        order.getUser();
        order.getOrderId();
        order.getItems();
        order.getOrderDate();
        order.getStatus();
        order.getTotalAmount();
        order.getDeliveryDate();

    }

    //w da apply sale
    public void applySale(Item item, Sale sale) {
        System.out.println("Sale is applied");
        double price_after_discount=item.setPrice(item.getPrice()*sale.getDiscountPercentage());
        System.out.println("The new price after discount is: "+ price_after_discount);
    }
}

