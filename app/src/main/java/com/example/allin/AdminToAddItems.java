package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class AdminToAddItems extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        EditText label=findViewById(R.id.ItemName);
        EditText info=findViewById(R.id.ItemInfo);
        EditText price=findViewById(R.id.Price);
        EditText stockQuantity=findViewById(R.id.StockQuantity);
        EditText category=findViewById(R.id.Category);

        Button addbtn=findViewById(R.id.addbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });








    }

}
