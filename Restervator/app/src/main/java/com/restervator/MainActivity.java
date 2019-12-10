package com.restervator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.restervator.api.ApiResponseListener;
import com.restervator.api.ZomatoClient;
import com.restervator.model.Restaurant;
import com.restervator.model.RestaurantCollection;
import com.restervator.model.SearchResponse;

import java.util.Optional;

public class MainActivity extends AppCompatActivity implements ApiResponseListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ZomatoClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new ZomatoClient(this);
    }

    @Override
    protected void onResume() {
        super.onResume();


        client.search();
    }

    @Override
    public void onSearchResponse(SearchResponse response) {

        // example usage:
        Optional<RestaurantCollection> firstResult = response.getRestaurants().stream().findFirst();

        if (firstResult.isPresent()) {
            Restaurant firstRestaurant = firstResult.get().getRestaurant();
            Log.d(LOG_TAG, firstRestaurant.getName());
            Log.d(LOG_TAG, firstRestaurant.getLocation().getFullAddress());
            Log.d(LOG_TAG, firstRestaurant.getUserRating().getAverageRating());
            // ...
        }
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, RestaurantActivity.class);
        startActivity(intent);
    }
}
