package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class AdminOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order);
        ListView lv;
        lv = findViewById(R.id.adminorderslist);

        DbHelper dbHelper = new DbHelper(this);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();

        OrderAdapter orderAdapter = new OrderAdapter(AdminOrderActivity.this,R.layout.orderdesign,system.getOrders(),"admin");
        lv.setAdapter(orderAdapter);
    }
}