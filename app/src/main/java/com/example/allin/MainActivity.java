package com.example.allin;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);
        dbHelper.insertDummyUserData();
        dbHelper.insertDummyItem();
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        system.loadUsersFromDatabase(dbHelper);
        system.loadItemsFromDatabase(dbHelper);
        system.InitializeAppData(dbHelper);
        Item i = new Item();

        i.setItemName("Iphone 15 pro ekhs");
        system.getItemsList().add(i);
        ListView lv = findViewById(R.id.itemslist);
        ItemAdapter ouradapter = new ItemAdapter(system.getItemsList());
        lv.setAdapter(ouradapter);

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}