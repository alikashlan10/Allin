package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class UserFeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed_back);

        Button submitbtn= findViewById(R.id.submitfeedbtn);
        EditText usercomment = findViewById(R.id.feedbackcomment);
        RatingBar ratingbar = findViewById(R.id.ratingbar);
        Intent intent = getIntent();
        int itemID= intent.getIntExtra("id",0);
        OnlineShoppingSystem system=OnlineShoppingSystem.getInstance();


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = usercomment.getText().toString();
                int rating = ratingbar.getNumStars();
                ((User)system.getCurrentPerson()).AddFeedBack(itemID,comment,rating,new DbHelper(UserFeedBackActivity.this));
                finish();
            }
        });


    }
}