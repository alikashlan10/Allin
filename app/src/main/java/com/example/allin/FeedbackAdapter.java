package com.example.allin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FeedbackAdapter extends BaseAdapter {
    private Context c;
    private int resource;
    private List<Feedback> feedbacks;

    public FeedbackAdapter(Context c, int resource, List<Feedback> feedbacks)
    {
        this.c = c;
        this.resource=resource;
        this.feedbacks =feedbacks;
    }
    @Override
    public int getCount() {
        return feedbacks.size();
    }

    @Override
    public Feedback getItem(int position) {
        return feedbacks.get(position);
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

        TextView usernameTextView = v.findViewById(R.id.username_feedback);
        TextView commentTextView = v.findViewById(R.id.feedback_comment);

        Feedback feedback = getItem(position);
        usernameTextView.setText(String.valueOf("User ID: "+feedback.getUser()));
        commentTextView.setText(feedback.getComment());

        return v;
    }

}
