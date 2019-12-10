package com.restervator.model;

import com.google.gson.annotations.SerializedName;

public class RestaurantCollection {

    @SerializedName("restaurant")
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
