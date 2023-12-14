package com.example.allin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ALl_In_DB";
    private static final int DATABASE_VERSION = 1;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static DbHelper instance;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// Creation strings (sql statements) //////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    // User table
    private static final String CREATE_USER = "CREATE TABLE User (" +
            "UserID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Username TEXT UNIQUE, " +
            "Password TEXT, " +
            "FullName TEXT, " +
            "SSN TEXT UNIQUE, " +
            "CreditCardNumber TEXT UNIQUE, " +
            "Email TEXT UNIQUE, " +
            "AddressID INTEGER,"+
            "FOREIGN KEY (AddressID) REFERENCES UserAddress(AddressID))";


    // UserAddress table
    private static final String CREATE_USER_ADDRESS = "CREATE TABLE UserAddress (" +
            "AddresID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Country TEXT, "+
            "City TEXT, "+
            "Street TEXT, "+
            "BuildingNum TEXT, "+
            "FlatNum INTEGER) ";

    // Category table
    private static final String CREATE_CATEGORY = "CREATE TABLE Category(" +
            "CategoryID INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "CategoryName TEXT UNIQUE) ";


    // Orders table
    private static final String CREATE_ORDER = "CREATE TABLE ORDERS(" +
            "OrderID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "UserID INTEGER, " +
            "OrderDate TEXT, " +
            "TotalAmount REAL, " +
            "DeliveryDate TEXT, " +
            "Status TEXT,"+
            "FOREIGN KEY (UserID) REFERENCES User(UserID))";


    // Item table
    private static final String CREATE_ITEM = "CREATE TABLE Item (" +
            "ItemID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Label TEXT, " +
            "ItemInfo TEXT, " +
            "Price REAL, " +
            "StockQuantity INTEGER, " +
            "CategoryID INTEGER, " +
            "SaleID INTEGER ,"+
            "FOREIGN KEY (SaleID) REFERENCES Sale(SaleID),"+
            "FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID))";


    //ItemImages table
    private static final String CREATE_ITEM_IMAGES = "CREATE TABLE ItemImages(" +
            "ItemImageID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ItemID INTEGER, "+
            "Image BLOB, "+
            "FOREIGN KEY (ItemId) REFERENCES Item(ItemId))";



    // CartItem
    private static final String CREATE_CART_ITEM = "CREATE TABLE CartItem (" +
            "CartItemID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "UserID INTEGER, " +
            "ItemID INTEGER, " +
            "Quantity INTEGER, " +
            "FOREIGN KEY (UserID) REFERENCES User(UserID), " +
            "FOREIGN KEY (ItemID) REFERENCES Item(ItemID))";


    //Admin table
    private static final String CREATE_ADMIN = "CREATE TABLE Admin (" +
            "AdminID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Username TEXT UNIQUE, " +
            "Password TEXT)";


    //Feedback table
    private static final String CREATE_FEEDBACK = "CREATE TABLE Feedback (" +
            "FeedbackID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Comment TEXT, " +
            "Rating INTEGER, " +
            "UserID INTEGER, " +
            "ItemID INTEGER, " +
            "FOREIGN KEY (UserID) REFERENCES User(UserID), " +
            "FOREIGN KEY (ItemID) REFERENCES Item(ItemID))";


    //OrderItem table
    private static final String CREATE_ORDER_ITEM = "CREATE TABLE OrderItem (" +
            "OrderItemID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Quantity INTEGER, " +
            "OrderID INTEGER, " +
            "ItemID INTEGER, " +
            "FOREIGN KEY (SaleID) REFERENCES Sale(SaleID),"+
            "FOREIGN KEY (OrderID) REFERENCES Orders(OrderID), " +
            "FOREIGN KEY (ItemID) REFERENCES Item(ItemID))";








    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_USER_ADDRESS);
        db.execSQL(CREATE_CATEGORY);
        db.execSQL(CREATE_ITEM);
        db.execSQL(CREATE_CART_ITEM);
        db.execSQL(CREATE_ORDER);
        db.execSQL(CREATE_ORDER_ITEM);
        db.execSQL(CREATE_ADMIN);
        db.execSQL(CREATE_FEEDBACK);
        db.execSQL(CREATE_ITEM_IMAGES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Initialized data for testing
    public void insertDummyUserData() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Username", "testuser");
        values.put("Password", "testpassword");
        values.put("FullName", "Test User");
        values.put("SSN", "123-45-6789");
        values.put("CreditCardNumber", "1234-5678-9012-3456");
        values.put("Email", "testuser@example.com");
        values.put("AddressID", 1); // Assuming you have an AddressID of 1 for testing

        db.insert("User", null, values);

        ContentValues adValues = new ContentValues();
        adValues.put("Username", "testadmin");
        adValues.put("Password", "testadmin");

        db.insert("Admin", null, adValues);
        db.close();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////// Custom functions ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////






    // load all users
    @SuppressLint("Range")
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        //raw query to select all users
        String Query = "SELECT * FROM User";
        //Executing the raw query
        Cursor cursor = db.rawQuery(Query, null);
        // Iterating through the table and add users(all their info) to the list
        while (cursor.moveToNext()) {
            User user = new User();
            user.setPersonID(cursor.getInt(cursor.getColumnIndex("UserID")));
            user.setUserName(cursor.getString(cursor.getColumnIndex("Username")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
            user.setAddressID(cursor.getInt(cursor.getColumnIndex("AddressID")));
            user.setCreditCard(cursor.getString(cursor.getColumnIndex("CreditCardNumber")));
            user.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
            user.setSSN(cursor.getString(cursor.getColumnIndex("SSN")));
            user.setFullName(cursor.getString(cursor.getColumnIndex("FullName")));
            userList.add(user);
        }
        // Close the cursor and database
        cursor.close();
        db.close();
        return userList;
    }
    @SuppressLint("Range")
    public List<Admin> getAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        //raw query to select all users
        String Query = "SELECT * FROM Admin";
        //Executing the raw query
        Cursor cursor = db.rawQuery(Query, null);
        // Iterating through the table and add users(all their info) to the list
        while (cursor.moveToNext()) {
            Admin admin = new Admin();
            admin.setPersonID(cursor.getInt(cursor.getColumnIndex("AdminID")));
            admin.setUserName(cursor.getString(cursor.getColumnIndex("Username")));
            admin.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
            adminList.add(admin);
        }
        // Close the cursor and database
        cursor.close();
        db.close();
        return adminList;
    }


    // load all categories
    @SuppressLint("Range")
    public List<Category> getAllCategories()
    {
        List<Category> CategoriesList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        //raw query to select all categories
        String Query="SELECT * FROM Category";

        //Executing the raw query
        Cursor cursor = db.rawQuery(Query, null);

        // Iterate through the table and add categories to the list
        while (cursor.moveToNext()) {

            Category cat = new Category();

            cat.setCategoryId(cursor.getInt(cursor.getColumnIndex("CategoryID")));
            cat.setCategoryName(cursor.getString(cursor.getColumnIndex("CategoryName")));


            CategoriesList.add(cat);
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return CategoriesList;

    }


    // Insertion of new user
    public long InsertNewUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        long userId = -1; // To store the ID of the inserted user

        try {
            db.beginTransaction();

            // Insert into UserAddress table
            ContentValues addressValues = new ContentValues();
            addressValues.put("Country", user.getUserAddress().getCountry());
            addressValues.put("City", user.getUserAddress().getCity());
            addressValues.put("Street", user.getUserAddress().getStreet());
            addressValues.put("BuildingNum", user.getUserAddress().getBuildingNum());
            addressValues.put("FlatNum", user.getUserAddress().getFlatNum());

            long addressId = db.insert("UserAddress", null, addressValues);

            // Insert into User table
            ContentValues userValues = new ContentValues();
            userValues.put("Username", user.getUserName());
            userValues.put("Password", user.getPassword());
            userValues.put("FullName", user.getFullName());
            userValues.put("SSN", user.getSSN());
            //userValues.put("CreditCardNumber", user.getCreditCard());
            userValues.put("Email", user.getEmail());
            userValues.put("AddressID", addressId); // Foreign key reference

            userId = db.insert("User", null, userValues);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        return userId;
    }
    // Deletion of new user
    public void DeleteUser(User user)
    {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            // Raw SQL query to delete the item by ID
            String deleteQuery = "DELETE FROM User WHERE UserID = ?";
            db.execSQL(deleteQuery, new Object[]{user.getPersonID()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }



    // Insertion of new Cart Item
    public long InsertNewCartItem(CartItem item) {
        SQLiteDatabase db = getWritableDatabase();
        long CartItemID = -1; // To store the ID of the inserted CartItem

        try {
            db.beginTransaction();

            // Insert into CartItem table
            ContentValues CartItemValues = new ContentValues();

            CartItemValues.put("UserID", item.getUserID());
            CartItemValues.put("ItemID", item.getItem().getItemId());
            CartItemValues.put("Quantity", item.getQuantity());


            CartItemID = db.insert("CartItem", null, CartItemValues);


            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        return CartItemID;
    }
    // Update CartItem item quantity
    public void UpdateItemQuantityInCart(int cartItemID, int newQuantity) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            db.beginTransaction();

            // Raw SQL query to update CartItems table
            String Query = "UPDATE CartItem SET Quantity = ? WHERE CartItemID = ?";
            db.execSQL(Query, new Object[]{newQuantity, cartItemID});

            db.setTransactionSuccessful();


        } finally {
            db.endTransaction();
            db.close();
        }
    }
    // Delete Cart item
    public void DeleteCartItem(int CartItemID) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            db.beginTransaction();

            // Raw SQL query to delete the item by ID
            String deleteQuery = "DELETE FROM CartItem WHERE ID = ?";
            db.execSQL(deleteQuery, new Object[]{CartItemID});

            db.setTransactionSuccessful();

        } finally {
            db.endTransaction();
            db.close();
        }
    }


    // Add new item (with images)
    public Long insertNewItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Label", item.getItemName());
        values.put("ItemInfo", item.getDescription());
        values.put("Price", item.getPrice());
        values.put("StockQuantity", item.getStockQuantity());
        values.put("CategoryID", item.getCategory().getCategoryId());
        values.put("SaleID", item.getSale());  // Assuming SaleID is a foreign key in the Item table

        // Insert the values into the Item table
        long ItemID = db.insert("Item", null, values);


        // Handling images insertion
        if (ItemID != -1) {
            List<byte[]> images = item.getImages();
            for (byte[] image : images) {
                ContentValues valuesImage = new ContentValues();
                valuesImage.put("ItemID", ItemID);
                valuesImage.put("Image", image);

                // Insert the values into the ItemImages table for each image
                db.insert("ItemImages", null, valuesImage);
            }
        }
        db.close();  // Close the database after insertion

        return ItemID;
    }
    // Delete item
    public void DeleteItem(int ItemID) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            // Raw SQL query to delete the item by ID
            String deleteQuery = "DELETE FROM Item WHERE ID = ?";
            db.execSQL(deleteQuery, new Object[]{ItemID});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    // Add new Feedback
    long insertNewFeedback(String comment,int rating,int userID,int ItemID)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Comment", comment);
        values.put("Rating", rating);
        values.put("UserID",userID);
        values.put("ItemID",ItemID);

        //Insert values into the Feedback table
        long ID=db.insert("Feedback",null,values);

        return ID;

    }


    // Add New category
    public long insertNewCategory(Category newCategory) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CategoryName", newCategory.getCategoryName());
        // Insert the values into the Item table
        long CategoryID = db.insert("Category", null, values);
        db.close();  // Close the database after insertion
        return CategoryID;
    }
    // Delete category
    public void DeleteCategory(int categoryId) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            // Raw SQL query to delete the item by ID
            String deleteQuery = "DELETE FROM Category WHERE CategoryID = ?";
            db.execSQL(deleteQuery, new Object[]{categoryId});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }



    // Add order
    public long InsertNewOrder(int userID,String date,double total,String deliveryDate,String status,List<CartItem> items)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //set values of order
        values.put("UserID", userID );
        values.put("OrderDate",date);
        values.put("TotalAmount",total);
        values.put("DeliveryDate",deliveryDate);
        values.put("Status",status);

        //isert order in Order table
        long orderid = db.insert("ORDERS", null, values);


        //Insert All cart items in ordered items
        //////////////////////////////////////////////////////////////////////////////
        for (CartItem item : items)
        {
            InsertOrderedItem(item,(int)orderid);
        }
        //////////////////////////////////////////////////////////////////////////////

        return orderid;
    }


    // Add OrderItem
    public void InsertOrderedItem(CartItem item,int OrderID)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //set values of order
        values.put("Quantity" , item.getQuantity() );
        values.put("OrderID " , OrderID);
        values.put("ItemID"   , item.getItem().getItemId());

        //isert orderitem in OrderItem table
        db.insert("OrderItem", null, values);
    }

















    ////////////////// Not used

    // User login query that returns the ID if the user from the database if found and return -1 else
    @SuppressLint("Range")
    public int loginUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT UserID FROM User WHERE Username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        int userId = -1; // Default value if login fails

        if (cursor.moveToFirst()) {
            // User authentication successful
            userId = cursor.getInt(cursor.getColumnIndex("UserID"));
        }

        cursor.close();
        db.close();

        return userId;
    }
    // admin login query
    @SuppressLint("Range")
    public int loginAdmin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT AdminID FROM Admin WHERE Username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        int userId = -1; // Default value if login fails

        if (cursor.moveToFirst()) {
            // User authentication successful
            userId = cursor.getInt(cursor.getColumnIndex("AdminID"));
        }
        cursor.close();
        db.close();
        return userId;
    }
}
