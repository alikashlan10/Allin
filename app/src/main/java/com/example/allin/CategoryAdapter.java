package com.example.allin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context c;
    private int resource;
    private List<Category> categories;

    public CategoryAdapter(Context c, int resource, List<Category> categories)
    {
        this.c = c;
        this.resource=resource;
        this.categories = categories;
    }
    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null)
        {
            v = LayoutInflater.from(c).inflate(resource, null, false);
        }

        TextView categoryName = v.findViewById(R.id.categoryName);
        TextView categoryID = v.findViewById(R.id.categoryID);
        Button categoryEditButton = v.findViewById(R.id.categoryEditButton);
        Button categoryDeleteButton = v.findViewById(R.id.categoryDeleteButton);

        Category category = getItem(position);
        categoryName.setText(category.getCategoryName());
        categoryID.setText(String.valueOf(category.getCategoryId()));
        categoryEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to "AddCategoryActivity"
                Intent intent = new Intent(c, AddCategoryActivity.class);
                //Sent the desire category's name with the intent
                intent.putExtra("categoryName",categoryName.getText().toString());
                c.startActivity(intent);
            }
        });
        categoryDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(c);
                dbHelper.DeleteCategory(category.getCategoryId());
                categories.remove(position);
                //refresh
                notifyDataSetChanged();
            }
        });


        return v;
    }

}
