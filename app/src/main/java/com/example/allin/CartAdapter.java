package com.example.allin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class CartAdapter extends BaseAdapter {
    private Context c;
    private int resource;
    private List<CartItem> UserCart;

    public CartAdapter(Context c, int resource, List<CartItem> cartitems)
    {
        this.c = c;
        this.resource=resource;
        this.UserCart=cartitems;
    }
    @Override
    public int getCount() {
        return UserCart.size();
    }

    @Override
    public CartItem getItem(int position) {
        return UserCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null)
        {
            v = LayoutInflater.from(c).inflate(resource, null, false);
        }
        TextView itemNameTextView = v.findViewById(R.id.cartitemtitle);
        TextView itemDescriptionTextView = v.findViewById(R.id.cartitemdesc);
        TextView itemPriceTextView = v.findViewById(R.id.cartitemprice);
        TextView itemQuantityTextView = v.findViewById(R.id.cartitemstockquantity);
        TextView ChosenQuantityTextView = v.findViewById(R.id.cartitemchosenquantity);
        TextView CartItemTotalPrice = v.findViewById(R.id.cartitemtotalprice);
        ImageView Quantityplusicon = v.findViewById(R.id.cartitemplusicon);
        ImageView Quantityminusicon = v.findViewById(R.id.cartitemminusicon);


        CartItem cart = getItem(position);
        itemNameTextView.setText(cart.getItem().getItemName());
        itemDescriptionTextView.setText(cart.getItem().getDescription());
        itemPriceTextView.setText(String.format(Locale.getDefault(), "$%.2f", cart.getItem().getPrice()));
        itemQuantityTextView.setText(String.valueOf("Stock Quantity: "+ cart.getItem().getStockQuantity()));
        ChosenQuantityTextView.setText(cart.getQuantity());

        Quantityplusicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringValue = ChosenQuantityTextView.getText().toString();
                int x = Integer.parseInt(stringValue);
                if(x<(cart.getItem().getStockQuantity())) {
                    ChosenQuantityTextView.setText(String.valueOf(++x));
                }

            }
        });

        Quantityminusicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringValue = ChosenQuantityTextView.getText().toString();
                int x = Integer.parseInt(stringValue);
                if(x!=0) {
                    ChosenQuantityTextView.setText(String.valueOf(--x));
                }
            }
        });

        return v;
    }

}
