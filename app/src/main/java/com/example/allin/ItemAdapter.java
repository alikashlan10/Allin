package com.example.allin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// ItemAdapter.java for ListView
// ItemAdapter.java for ListView
public class ItemAdapter extends BaseAdapter {

    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.design, parent, false);

        Item item = itemList.get(position);

        ImageView itemImageView = view.findViewById(R.id.itemImageView);
        TextView itemNameTextView = view.findViewById(R.id.itemNameTextView);
        TextView itemDescriptionTextView = view.findViewById(R.id.itemDescriptionTextView);
        TextView itemPriceTextView = view.findViewById(R.id.itemPriceTextView);
        Button addToCartButton = view.findViewById(R.id.addToCartButton);

        // Set item information in the view
        itemNameTextView.setText(item.getItemName());
        itemDescriptionTextView.setText(item.getDescription());
        itemPriceTextView.setText(String.format(Locale.getDefault(), "$%.2f", item.getPrice()));

        // Load the first image into the ImageView (you may need to implement image loading)
        byte[] firstImageBytes = item.getImages().get(0);
        Bitmap bitmap = BitmapFactory.decodeByteArray(firstImageBytes, 0, firstImageBytes.length);
        itemImageView.setImageBitmap(bitmap);

        // Set click listener for the "Add to Cart" button
        addToCartButton.setOnClickListener(v -> addToCart(item));

        return view;
    }

    private void addToCart(Item item) {
        // Implement logic to add the item to the cart
        // This could involve updating a shopping cart object or database
        // ...
    }
}


