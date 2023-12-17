package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ListView lv;
        lv = findViewById(R.id.orderlistview);
        DbHelper dbHelper = new DbHelper(this);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();


        OrderAdapter orderAdapter = new OrderAdapter(OrderActivity.this,R.layout.orderdesign,((User)system.getCurrentPerson()).GetMyOrders(),"user");
        lv.setAdapter(orderAdapter);
    }
}