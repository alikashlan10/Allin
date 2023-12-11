package com.example.allin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);


        DbHelper dbHelper=new DbHelper(this);
        Person u = PersonFactory.GetPerson("user");
        u.login("a","b");


    }
}