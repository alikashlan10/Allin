package com.example.allin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context c;
    private int resource;
    private List<User> users;

    public UserAdapter(Context c, int resource, List<User> users)
    {
        this.c = c;
        this.resource=resource;
        this.users = users;
    }
    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null)
        {
            v = LayoutInflater.from(c).inflate(resource, null, false);
        }

        TextView username = v.findViewById(R.id.username);
        TextView userID = v.findViewById(R.id.userID);
        Button categoryDeleteButton = v.findViewById(R.id.categoryDeleteButton);

        User user = getItem(position);
        username.setText("Username: "+user.getUserName());
        userID.setText(String.valueOf("User ID: "+user.getPersonID()));
        categoryDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(c);
                dbHelper.DeleteUser(user);
                users.remove(position);
                //refresh
                notifyDataSetChanged();
            }
        });
        return v;
    }

}
