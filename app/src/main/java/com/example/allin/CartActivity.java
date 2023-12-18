package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ListView lv;
        lv = findViewById(R.id.test_list2);


        DbHelper dbHelper = new DbHelper(this);
        OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
        CartAdapter beforeCheckOutAdapter = new CartAdapter(this,R.layout.cartdesign,((User)system.getCurrentPerson()).getCart(),"beforeCheckOut");
        lv.setAdapter(beforeCheckOutAdapter);
        CartAdapter afterCheckoutAdapter = new CartAdapter(this,R.layout.cartdesign,((User)system.getCurrentPerson()).getCart(),"aftercheckout");


        Button checkoutbtn = findViewById(R.id.checkout_button);
        Button placeorderbtn = findViewById(R.id.placeorder_button);
        TextView totalpricetext = findViewById(R.id.checkoutprice);
        ImageView refreshbtn = findViewById(R.id.refreshbtn);


        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((User)system.getCurrentPerson()).getCart().size()!=0) {
                    lv.setAdapter(afterCheckoutAdapter);
                    totalpricetext.setText(String.valueOf(((User) system.getCurrentPerson()).getCartTotalPrice()));
                    checkoutbtn.setVisibility(View.GONE);
                    placeorderbtn.setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Cart is empty", Toast.LENGTH_SHORT).show();
                }

            }

        });

        placeorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.setAdapter(beforeCheckOutAdapter);
                if(!totalpricetext.getText().toString().equals("-") && beforeCheckOutAdapter.getCount()!=0) {
                    system.placeOrder(((User) system.getCurrentPerson()), dbHelper);
                    Toast.makeText(getApplicationContext(), "Order placed successfuly", Toast.LENGTH_LONG).show();
                }
                else if(((User)system.getCurrentPerson()).getCart().size()==0)
                {
                    Toast.makeText(getApplicationContext(), "Cart is empty", Toast.LENGTH_SHORT).show();
                }

                totalpricetext.setText("-");
            }

        });

        refreshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.setAdapter(beforeCheckOutAdapter);
            }
        });



    }
}