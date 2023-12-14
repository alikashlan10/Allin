/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.allin;

/**
 *
 * @author Ali
 */
public class Feedback {
    private int feedbackId;
    private int userID;
    private int itemID;
    private String comment;
    private int rating;

    public Feedback(int feedbackId, int user, int item, String comment, int rating) {
        this.feedbackId = feedbackId;
        this.userID = user;
        this.itemID = item;
        this.comment = comment;
        this.rating = rating;
    }

    public Feedback() {}

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUser() {
        return userID;
    }

    public void setUser(int user) {
        this.userID = user;
    }

    public int getItem() {
        return itemID;
    }

    public void setItem(int item) {
        this.itemID = item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}


