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

        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        DbHelper dbHelper = new DbHelper(this);
        dbHelper.insertDummyUserData();
        dbHelper.insertDummyCategories();
        dbHelper.insertDummyItem();
        dbHelper.insertDummyAdmin();
        //system.loadItemsFromDatabase(dbHelper);
        system.InitializeAppData(dbHelper);


        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}