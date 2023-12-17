package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ListView lv;
        lv = findViewById(R.id.categoryList);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        CategoryAdapter categoryAdapter = new CategoryAdapter(this,R.layout.category_design,system.getCategories());
        lv.setAdapter(categoryAdapter);
    }
}