package com.restervator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.restervator.adapter.RestaurantAdapter;
import com.restervator.api.SearchConfiguration;
import com.restervator.api.ZomatoClient;
import com.restervator.location.LocationFetcher;
import com.restervator.model.dataTransferObjects.SearchResponseDto;
import com.restervator.model.domain.Restaurant;
import com.restervator.utils.PermissionUtil;

import java.util.ArrayList;

import static com.restervator.converter.DtoToRestaurantConverter.convertToRestaurantList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ZomatoClient client;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private LocationFetcher locationFetcher;
    private RestaurantAdapter adapter;
    private ArrayList<Restaurant> restaurants;
    private RecyclerView rvRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup api client
        client = new ZomatoClient();

        // setup location fetcher
        locationFetcher = new LocationFetcher(this);

        // prompt user to turn on location
        PermissionUtil.askUserForLocationPermission(this, REQUEST_LOCATION_PERMISSION);

        rvRestaurants = findViewById(R.id.recyclerView);

        // add empty data to the adapter
        restaurants = new ArrayList<>();
        adapter = new RestaurantAdapter(MainActivity.this, restaurants);

        // Attach the adapter to the recyclerview to populate items
        rvRestaurants.setAdapter(adapter);

        // Set layout manager to position the items
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        // check if the response is from the call to allow fetching the location
        if (requestCode == REQUEST_LOCATION_PERMISSION) {

            // if location permission not granted, show a toast
            if (!PermissionUtil.isLocationPermissionGranted(grantResults)) {
                Toast.makeText(this,
                        R.string.location_permission_denied,
                        Toast.LENGTH_SHORT).show();
            }

            // get last known position and fetch the data from the API
            searchNearByRestaurants();
        }
    }


    // search restaurants based on the current location.
    private void searchNearByRestaurants() {
        // get last known location, add asynchronous callback.
        locationFetcher.getLastKnownLocation(location -> {
            Log.d(LOG_TAG, "received location: " + location.toString());

            // use the fluent builder to create a configuration for the Zomato API.
            SearchConfiguration configuration = new SearchConfiguration.Builder()
                    .nearLocation(location)
                    .sortRestaurantsBy(SearchConfiguration.SortRestaurantsBy.REAL_DISTANCE)
                    .withSortOrder(SearchConfiguration.SortOrder.ASC)
                    .limitNumberOfResults(10)
                    .build();

            // trigger a search, add asynchronous callback.
            client.search(configuration, this::displayData);
        });
    }

    // convert remote data and display the data to the user interface
    private void displayData(SearchResponseDto dto) {
        // empty the adapter
        this.restaurants.clear();

        // convert the api data to our domain model and add it to the adapter
        this.restaurants.addAll(new ArrayList<>(convertToRestaurantList(dto)));

        // notify the adapter that the data has changed
        adapter.notifyDataSetChanged();
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
