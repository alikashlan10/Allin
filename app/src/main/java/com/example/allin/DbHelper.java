package com.example.allin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
            "Iteminfo TEXT, " +
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
            "SaleID INTEGER, "+
            "FOREIGN KEY (SaleID) REFERENCES Sale(SaleID),"+
            "FOREIGN KEY (OrderID) REFERENCES Orders(OrderID), " +
            "FOREIGN KEY (ItemID) REFERENCES Item(ItemID))";


    //Sale table
    private static final String CREATE_SALE = "CREATE TABLE Sale("+
            "SaleID INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "StartDate TEXT ,"+
            "EndDate TEXT ," +
            "DiscountPercentage REAL )";





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
        db.execSQL(CREATE_SALE);
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
        db.close();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////// Custom functions ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


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
            userId = cursor.getInt(cursor.getColumnIndex("id"));
        }

        cursor.close();
        db.close();

        return userId;
    }

    @SuppressLint("Range")
    public int loginAdmin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT AdminID FROM Admin WHERE Username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        int userId = -1; // Default value if login fails

        if (cursor.moveToFirst()) {
            // User authentication successful
            userId = cursor.getInt(cursor.getColumnIndex("id"));
        }

        cursor.close();
        db.close();

        return userId;
    }






}
