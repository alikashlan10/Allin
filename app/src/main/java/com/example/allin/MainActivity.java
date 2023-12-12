package com.example.allin;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);
        dbHelper.insertDummyUserData();
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        system.loadUsersFromDatabase(dbHelper);

        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }
}