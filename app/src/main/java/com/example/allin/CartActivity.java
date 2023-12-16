package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ListView lv;
        lv = findViewById(R.id.test_list2);

        DbHelper dbHelper = new DbHelper(this);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        //system.loadUsersFromDatabase(dbHelper);
        //system.InitializeAppData(dbHelper);
        //system.loadItemsFromDatabase(dbHelper);

        //List<Item> testing = new ArrayList<>();
        CartAdapter testadapter = new CartAdapter(this,R.layout.cart_design,((User)system.getCurrentPerson()).getCart());
        lv.setAdapter(testadapter);

        Button checkoutbtn = findViewById(R.id.checkout_button);
        Button placeorderbtn = findViewById(R.id.placeorder_button);
        TextView totalpricetext = findViewById(R.id.checkoutprice);

        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalpricetext.setText(String.valueOf(((User)system.getCurrentPerson()).getCartTotalPrice()));
                checkoutbtn.setVisibility(View.GONE);
                placeorderbtn.setVisibility(View.VISIBLE);
            }

        });

        placeorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                system.placeOrder(((User)system.getCurrentPerson()), dbHelper);
            }

        });

    }
}