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


        List<Feedback> fdbkLst = new ArrayList<>();
        Feedback fd = new Feedback(3,3,3,"aaaaaas",3);
        fdbkLst.add(fd);
        fdbkLst.add(fd);

        FeedbackAdapter testadapter = new FeedbackAdapter(this,R.layout.feedback_design,fdbkLst);
        lv.setAdapter(testadapter);



    }
}