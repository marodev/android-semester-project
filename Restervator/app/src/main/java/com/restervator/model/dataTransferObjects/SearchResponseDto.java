package com.restervator.model.dataTransferObjects;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;

public class SearchResponseDto {

    @SerializedName("restaurants")
    private Collection<RestaurantCollectionDto> restaurants;

    public SearchResponseDto() {
        this.restaurants = new ArrayList<>();
    }

    public Collection<RestaurantCollectionDto> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<RestaurantCollectionDto> restaurants) {
        this.restaurants = restaurants;
    }
}
