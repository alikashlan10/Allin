package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView lv;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        lv = findViewById(R.id.test_list);
        Button b = findViewById(R.id.gotocartbtn);



        DbHelper dbHelper = new DbHelper(this);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        system.loadUsersFromDatabase(dbHelper);
        system.InitializeAppData(dbHelper);
        system.loadItemsFromDatabase(dbHelper);

        List<Item> testing = new ArrayList<>();
        HomeAdapter testadapter = new HomeAdapter(this,R.layout.itemdesign,system.getItemsList());
        lv.setAdapter(testadapter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,CartActivity.class);
                startActivity(i);
            }
        });


        Spinner catspinner = findViewById(R.id.Catspinner);

        // Create an ArrayAdapter using the ArrayList
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, system.getCateogriesNames());

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        catspinner.setAdapter(adapter);
        catspinner.setSelection(-1);
        catspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Perform the action when an item is selected
                String selectedValue = (String) parentView.getItemAtPosition(position);
                HomeAdapter filteredadapter = new HomeAdapter(HomeActivity.this,R.layout.itemdesign,system.getItemByCategory(selectedValue));
                lv.setAdapter(filteredadapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });

        ImageView searchicon = findViewById(R.id.Searchicon);
        EditText searchbar = findViewById(R.id.Searchbar);
        searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x = searchbar.getText().toString();
                HomeAdapter filteredadapter = new HomeAdapter(HomeActivity.this,R.layout.itemdesign, system.SearchByText(x));
                lv.setAdapter(filteredadapter);
            }

        });


    }
}