package com.restervator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.restervator.RestaurantActivity.RESTAURANT_REPLY;

public class BookingActivity extends AppCompatActivity {
    String restaurantName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        restaurantName = getIntent().getExtras().getString(RESTAURANT_REPLY);

        TextView restaurantNameView = findViewById(R.id.bookingRestaurantName);
        restaurantNameView.setText(restaurantName);
    }

    //handle the date chosen in calendar
    //handle time
    //handle num of persons
    //save info in database
    //display the information in ReservationOverviewActivity
    //add swipe to delete

    public void bookRestaurant(View view) {
        Toast.makeText(this, restaurantName + " has been reserved",
                Toast.LENGTH_LONG).show();


        Intent intent = new Intent(this, ReservationOverviewActivity.class);
        startActivity(intent);
    }
}
