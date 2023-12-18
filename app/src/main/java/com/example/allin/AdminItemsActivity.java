package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class AdminItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_items);
        ListView lv;
        lv = findViewById(R.id.Adminitemslist);
        DbHelper dbHelper = new DbHelper(this);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        HomeAdapter adminitemadapter = new HomeAdapter(this, R.layout.itemdesign, system.getItemsList(), "admin");
        lv.setAdapter(adminitemadapter);
        Button add=findViewById(R.id.adminadditembtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminItemsActivity.this,AdminToAddItems.class);
                startActivity(i);
            }
        });
    }
}