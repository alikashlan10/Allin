package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        Button usersbtn = findViewById(R.id.adminpanelusersbtn);
        Button ordersbtn = findViewById(R.id.adminpanelordersbtn);
        Button catsbtn = findViewById(R.id.adminpanelcatsbtn);
        Button itemsbtn = findViewById(R.id.adminpanelitemsbtn);
        Button feedbtn = findViewById(R.id.adminpanelfeedbtn);

        usersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i=new Intent(AdminPanelActivity.this,CartActivity.class);
               // startActivity(i);
            }
        });

        ordersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminPanelActivity.this, AdminOrderActivity.class);
                startActivity(i);
            }
        });

        catsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminPanelActivity.this,CategoryActivity.class);
                startActivity(i);
            }
        });

        itemsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminPanelActivity.this,AdminItemsActivity.class);
                startActivity(i);
            }
        });

        feedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminPanelActivity.this,FeedbackActivity.class);
                startActivity(i);
            }
        });
    }
}