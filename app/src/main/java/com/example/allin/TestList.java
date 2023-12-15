package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TestList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView lv;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        lv = findViewById(R.id.test_list);

        DbHelper dbHelper = new DbHelper(this);
        dbHelper.insertDummyUserData();
        dbHelper.insertDummyItem();
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        system.loadUsersFromDatabase(dbHelper);
        system.InitializeAppData(dbHelper);
        system.loadItemsFromDatabase(dbHelper);

        List<Item> testing = new ArrayList<>();
        ItemAdapter2 testadapter = new ItemAdapter2(this,R.layout.itemdesign,system.getItemsList());
        lv.setAdapter(testadapter);
    }
}