package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserFeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed_back);
        Button submitbtn= findViewById(R.id.submitfeedbtn);
        EditText usercomment = findViewById(R.id.feedback_comment);
        Intent intent = getIntent();
        int id= Integer.parseInt(intent.getStringExtra("id"));
        OnlineShoppingSystem system=OnlineShoppingSystem.getInstance();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((User)system.getCurrentPerson()).AddFeedBack();
            }
        });
    }
}