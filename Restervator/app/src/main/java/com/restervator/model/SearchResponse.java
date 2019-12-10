package com.restervator.model;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class SearchResponse {


    @SerializedName("restaurants")
    private Collection<RestaurantCollection> restaurants;

    public Collection<RestaurantCollection> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<RestaurantCollection> restaurants) {
        this.restaurants = restaurants;
    }
}
