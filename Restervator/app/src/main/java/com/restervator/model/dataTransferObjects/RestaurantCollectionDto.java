package com.restervator.model.dataTransferObjects;

import com.google.gson.annotations.SerializedName;

public class RestaurantCollectionDto {

    @SerializedName("restaurant")
    private RestaurantDto restaurantDto;

    public RestaurantDto getRestaurant() {
        return restaurantDto;
    }

    public void setRestaurant(RestaurantDto restaurantDto) {
        this.restaurantDto = restaurantDto;
    }
}
