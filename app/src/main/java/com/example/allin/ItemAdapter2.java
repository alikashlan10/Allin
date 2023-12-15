package com.example.allin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class ItemAdapter2 extends BaseAdapter {
    private Context c;
    private int resource;
    private List<Item> items;

    public ItemAdapter2(Context c, int resource, List<Item> items)
        {
            this.c = c;
            this.resource=resource;
            this.items=items;
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
        Button cartbtn = v.findViewById(R.id.addtocartbtn);

        Item i = getItem(position);
        itemNameTextView.setText(i.getItemName());
        itemDescriptionTextView.setText(i.getDescription());
        itemPriceTextView.setText(String.format(Locale.getDefault(), "$%.2f", i.getPrice()));

        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the button click event
                OnlineShoppingSystem system=OnlineShoppingSystem.getInstance();
                User user = new User();
                DbHelper dphelper = new DbHelper(c);
                user.setUserName("Ali");
                user.setPersonID(123);
                system.users.add(user);
                user.AddItemsToCart(i, 3, user.getPersonID(), dphelper);
            }

        });

        return v;
    }

}
