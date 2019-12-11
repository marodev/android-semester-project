package com.restervator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.restervator.model.RestaurantList;

import java.util.ArrayList;

public class MainInterface extends AppCompatActivity {

    ArrayList<RestaurantList> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

        RecyclerView rvRestaurants = (RecyclerView) findViewById(R.id.recyclerView);

        restaurants = RestaurantList.createRestaurantList(10);

        ListAdapter adapter = new ListAdapter(restaurants);
        // Attach the adapter to the recyclerview to populate items
        rvRestaurants.setAdapter(adapter);
        // Set layout manager to position the items
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));


    }
}
