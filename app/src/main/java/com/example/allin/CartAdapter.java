package com.example.allin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class CartAdapter extends BaseAdapter {
    private Context c;
    private int resource;
    private List<CartItem> UserCart;
    private String type;

    public CartAdapter(Context c, int resource, List<CartItem> cartitems,String type)
    {
        this.c = c;
        this.resource=resource;
        this.UserCart=cartitems;
        this.type=type;
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
        ImageView cancelbtn = v.findViewById(R.id.cancelbtn);

        //After checkout user cant modify the order any more
        if(type.equals("aftercheckout"))
        {
            Quantityplusicon.setVisibility(View.GONE);
            Quantityminusicon.setVisibility(View.GONE);
            cancelbtn.setVisibility(View.GONE);
        }


        CartItem cartItem = getItem(position);

        itemNameTextView.setText(cartItem.getItem().getItemName());
        itemDescriptionTextView.setText(cartItem.getItem().getDescription());
        itemPriceTextView.setText(String.format(Locale.getDefault(), "$%.2f", cartItem.getItem().getPrice()));
        itemQuantityTextView.setText(String.valueOf("Stock Quantity: "+ cartItem.getItem().getStockQuantity()));
        ChosenQuantityTextView.setText(String.valueOf(cartItem.getQuantity()));
        CartItemTotalPrice.setText(String.valueOf(cartItem.CalculateSubTotal()));

        Quantityplusicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringValue = ChosenQuantityTextView.getText().toString();
                int x = Integer.parseInt(stringValue);
                if(x<(cartItem.getItem().getStockQuantity())) {
                    ChosenQuantityTextView.setText(String.valueOf(++x));
                    cartItem.setQuantity(x);
                    notifyDataSetChanged();
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
                    cartItem.setQuantity(x);
                    notifyDataSetChanged();
                }
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DbHelper dbHelper = new DbHelper(c);
                 OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
                 ((User)system.getCurrentPerson()).CancelItem(cartItem.getCartItemID(),dbHelper);
                CartItemTotalPrice.setText(String.valueOf(cartItem.CalculateSubTotal()));
                 Toast.makeText(c, "Iteam has been deleted, Refresh", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

}
