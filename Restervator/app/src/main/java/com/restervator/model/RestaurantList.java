package com.restervator.model;

import java.util.ArrayList;

public class RestaurantList {

    private boolean mOnline;
    private String mName;

    public RestaurantList(String name, boolean online) {
        mName = name;
        mOnline = online;
    }

    public String getName() {
        return mName;
    }

    public boolean isOnline() {
        return mOnline;
    }

    private static int lastRestaurantId = 0;

    public static ArrayList<RestaurantList> createRestaurantList(int numRestaurants) {
        ArrayList<RestaurantList> restaurants = new ArrayList<RestaurantList>();

        for (int i = 1; i < numRestaurants; i++) {
            restaurants.add(new RestaurantList("Name" + ++lastRestaurantId, i <= numRestaurants / 2));
        }

        return restaurants;
    }
}
