package com.restervator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.squareup.picasso.Picasso;


public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent = new Intent(this, BookingActivity.class);

        ExtendedFloatingActionButton fab = findViewById(R.id.fab);

        // set onclick listener for booking activity
        fab.setOnClickListener(view -> startActivity(intent));

        ImageView restaurantImageView = findViewById(R.id.imageView);

        // http request to fetch image and load into the view
        Picasso.get()
                .load("https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg")
                .into(restaurantImageView);
    }

}
