package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ListView lv;
        lv = findViewById(R.id.userList);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        UserAdapter userAdapter = new UserAdapter(this,R.layout.user_design,system.users);
        lv.setAdapter(userAdapter);


    }
}