package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ListView lv;
        lv = findViewById(R.id.test_list2);

        DbHelper dbHelper = new DbHelper(this);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        //system.loadUsersFromDatabase(dbHelper);
        //system.InitializeAppData(dbHelper);
        //system.loadItemsFromDatabase(dbHelper);

        //List<Item> testing = new ArrayList<>();
        CartAdapter testadapter = new CartAdapter(this,R.layout.cart_design,((User)system.getCurrentPerson()).getCart());
        lv.setAdapter(testadapter);
    }
}