package com.restervator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
    }

    public void openMap(View view) {
//        Intent intent = new Intent(this, MapActivity.class);
//        startActivity(intent);
    }

    public void bookRestaurant(View view) {
        Toast.makeText(this, "-Name of restaurant- has been reserved",
                Toast.LENGTH_LONG).show();


        Intent intent = new Intent(this, ReservationOverviewActivity.class);
        startActivity(intent);
    }
}
