package com.example.allin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
import java.util.Locale;

public class HomeAdapter extends BaseAdapter {
    private Context c;
    private int resource;
    private List<Item> items;

    String person;

    public HomeAdapter(Context c, int resource, List<Item> items, String person)
        {
            this.c = c;
            this.resource=resource;
            this.items=items;
            this.person=person;
        }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
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
        TextView itemNameTextView = v.findViewById(R.id.titletext);
        TextView itemDescriptionTextView = v.findViewById(R.id.descriptiontext);
        TextView itemPriceTextView = v.findViewById(R.id.pricetext);
        TextView itemQuantityTextView = v.findViewById(R.id.quantitytext);
        TextView ChosenQuantityTextView = v.findViewById(R.id.chosenquantity);
        ImageView Quantityplusicon = v.findViewById(R.id.plusicon);
        ImageView Quantityminusicon = v.findViewById(R.id.minusicon);
        Button cartbtn = v.findViewById(R.id.addtocartbtn);
        Button salebtn = v.findViewById(R.id.addsalebtn);


        Item i = getItem(position);
        itemNameTextView.setText(i.getItemName());
        itemDescriptionTextView.setText(i.getDescription());
        itemPriceTextView.setText(String.format(Locale.getDefault(), "$%.2f", i.getPrice()));
        itemQuantityTextView.setText(String.valueOf("Stock Quantity: "+i.getStockQuantity()));

        Quantityplusicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringValue = ChosenQuantityTextView.getText().toString();
                int x = Integer.parseInt(stringValue);
                if(x<(i.getStockQuantity())) {
                    ChosenQuantityTextView.setText(String.valueOf(++x));
                }

            }
        });

        if(person!="user")
        {
            cartbtn.setVisibility(View.GONE);
            Quantityplusicon.setVisibility(View.GONE);
            Quantityminusicon.setVisibility(View.GONE);
            ChosenQuantityTextView.setVisibility(View.GONE);
            salebtn.setVisibility(View.VISIBLE);
        }

        Quantityminusicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringValue = ChosenQuantityTextView.getText().toString();
                int x = Integer.parseInt(stringValue);
                if(x!=1) {
                    ChosenQuantityTextView.setText(String.valueOf(--x));
                }
            }
        });


        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the button click event
                OnlineShoppingSystem system=OnlineShoppingSystem.getInstance();
                DbHelper dphelper = new DbHelper(c);
                ((User)system.getCurrentPerson()).AddItemsToCart(i, Integer.parseInt(ChosenQuantityTextView.getText().toString()), system.getCurrentPerson().getPersonID(), dphelper);
            }

        });

        salebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i.getSale() == 0){
                    i.setSale(0.2F);
                    salebtn.setText("Remove Sale");
                }
                else{
                    i.setSale(0.0F);
                    salebtn.setText("Add Sale");
                }
                notifyDataSetChanged();
            }
        });

        return v;
    }

}
