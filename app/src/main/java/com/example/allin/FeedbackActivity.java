package com.example.allin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView lv;
        setContentView(R.layout.activity_feedback);
        lv = findViewById(R.id.feedbackList);

        OnlineShoppingSystem system=OnlineShoppingSystem.getInstance();
        system.loadFeedBacksFromDatabase(new DbHelper(this));
        List<Feedback> fdbkLst = system.getFeedbacks();

        FeedbackAdapter testadapter = new FeedbackAdapter(this,R.layout.feedback_design,fdbkLst);
        lv.setAdapter(testadapter);



    }
}