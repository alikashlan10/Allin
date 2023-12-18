package com.example.allin;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.List;
import java.util.Locale;

public class OrderAdapter extends BaseAdapter {
    private Context c;
    private int resource;
    private List<Order> orders;

    String person;

    public OrderAdapter(Context c, int resource, List<Order> orders, String person)
    {
        this.c = c;
        this.resource=resource;
        this.orders=orders;
        this.person=person;
    }
    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Order getItem(int position) {
        return orders.get(position);
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

        OnlineShoppingSystem sys=OnlineShoppingSystem.getInstance();
        DbHelper dbHelper=new DbHelper(c);

        TextView OrderTitleID = v.findViewById(R.id.ordertitleid);
        TextView OrderStatus = v.findViewById(R.id.orderstatus);
        TextView OrderStartDate = v.findViewById(R.id.orderstartdate);
        TextView OrderDeliveryDate = v.findViewById(R.id.orderdeliverydate);
        TextView OrderUserID = v.findViewById(R.id.orderuserid);
        TextView OrderTotalAmount = v.findViewById(R.id.ordertotalamount);
        ImageView OrderCancelButton = v.findViewById(R.id.cancelbtn2);
        ImageView OrderReorderbtn = v.findViewById(R.id.reorderbtn);

        Order o = getItem(position);

        OrderTitleID.setText("Order ID: "+String.valueOf(o.getOrderId()));
        OrderStatus.setText("Order Status: "+o.getStatus());
        OrderStartDate.setText("Order Date: "+o.getOrderDate());
        OrderDeliveryDate.setText("Order Delivery Date: "+o.getDeliveryDate());
        OrderUserID.setText(String.valueOf(o.getUserID()));
        OrderTotalAmount.setText(String.valueOf(o.getTotalAmount()));

        if(!person.equals("user"))
        {
            OrderCancelButton.setVisibility(View.GONE);
            OrderReorderbtn.setVisibility(View.GONE);
        }

        OrderCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnlineShoppingSystem system = OnlineShoppingSystem.getInstance();
                DbHelper dbHelper = new DbHelper(c);
                ((User)system.getCurrentPerson()).CancelOrder(o.getOrderId(),dbHelper);
                Toast.makeText(c, "Order is canceled", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        OrderReorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id=(int)dbHelper.InsertReOrder(o.getClone(0));
                sys.getOrders().add(o.getClone(id));
                Toast.makeText(c, "Re-ordered successfully", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();

            }
        });

        return v;
    }

}
