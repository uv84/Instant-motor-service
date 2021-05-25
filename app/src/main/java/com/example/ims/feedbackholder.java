package com.example.ims;

public class feedbackholder {
    String feedback;
    float rating;

    public feedbackholder() {
    }

    public feedbackholder(String feedback, float rating) {
        this.feedback = feedback;
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
