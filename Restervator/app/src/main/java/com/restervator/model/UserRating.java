package com.restervator.model;

import com.google.gson.annotations.SerializedName;

public class UserRating {

    @SerializedName("aggregate_rating")
    private String averageRating;

    @SerializedName("rating_text")
    private String ratingText;

    @SerializedName("rating_color")
    private String ratingColor;

    @SerializedName("votes")
    private int votes;

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
