package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        //get "categoryName" from "CategoryAdapter" in EDIT case
        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("categoryName");

        EditText addCategoryEditText = findViewById(R.id.addCategoryEditText);
        Button addCategorySubmitButton = findViewById(R.id.addCategorySubmitButton);
        TextView addcategorytextview = findViewById(R.id.addCategoryTextView);

        //EDIT case
        if(categoryName != null) {
            addCategoryEditText.setHint(categoryName);
            addcategorytextview.setText("Edit Category");
            addCategorySubmitButton.setText("Edit");
        }


        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        DbHelper dbHelper = new DbHelper(this);
        addCategorySubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(categoryName == null){ //ADD case
                    Category newCategory = new Category(addCategoryEditText.getText().toString());
                    int categoryID = (int)dbHelper.insertNewCategory(newCategory);
                    newCategory.setCategoryId(categoryID);
                    system.getCategories().add(newCategory);
                    Intent intent = new Intent(AddCategoryActivity.this, CategoryActivity.class);
                    startActivity(intent);
                }
                else{ //EDIT case
                    Category toBeEditCategory = system.getCategoryIdByName(categoryName);
                    toBeEditCategory.setCategoryName(addCategoryEditText.getText().toString());
                    //dbHelper.updateCategory();
                }
                //go back to "CategoryActivity"
                Intent intent = new Intent(AddCategoryActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });


    }
}