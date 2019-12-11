package com.restervator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    public void toMain(View view) {
        Intent intent = new Intent(this, MainInterface.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reservations) {
            Intent intent = new Intent(this, ReservationOverviewActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
