package com.restervator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;

import static com.restervator.RestaurantActivity.RESTAURANT_REPLY;

public class BookingActivity extends AppCompatActivity {

    public static final String RESERVATION_REPLY =
            "com.restervator.extra.REPLY";
    String restaurantName = "";
    String restaurantImage = "";
    CalendarView calendarView;
    MaterialButtonToggleGroup lunchButtonGroup;
    MaterialButtonToggleGroup dinnerButtonGroup;
    MaterialButtonToggleGroup personCountButtonGroup;
    String chosenDate;
    String chosenTime;
    String numOfPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        ArrayList<String> restaurantInformation = getIntent().getStringArrayListExtra(RESTAURANT_REPLY);
        restaurantName = restaurantInformation.get(0);
        restaurantImage = restaurantInformation.get(3);

        TextView restaurantNameView = findViewById(R.id.bookingRestaurantName);
        restaurantNameView.setText(restaurantName);


        // Get the selected date whenever a new date is picked in the calendar.
        calendarView = findViewById(R.id.calendarView);
        calendarView.setMinDate(System.currentTimeMillis());
        calendarView.setOnDateChangeListener((CalendarView view, int year, int month,
                                              int dayOfMonth) ->
                chosenDate = dayOfMonth + "/" + (month + 1) + "/" + year);


        // The lunch and dinner time were separated because the material design toggle button only
        // aligns items horizontally and did not wrap the items. so since we had 8 different times,
        // they could not all fit on the screen horizontally and thus we separated them
        // Gets the selected time chosen for lunch
        lunchButtonGroup = findViewById(R.id.lunch_time_toggle_group);
        lunchButtonGroup.addOnButtonCheckedListener((MaterialButtonToggleGroup group, int checkedId, boolean isChecked) -> {

            MaterialButton button = findViewById(checkedId);
            chosenTime = button.getText().toString();
        });
        // Gets the selected time chosen for dinner
        dinnerButtonGroup = findViewById(R.id.lunch_time_toggle_group);
        dinnerButtonGroup.addOnButtonCheckedListener((MaterialButtonToggleGroup group, int checkedId, boolean isChecked) -> {

            MaterialButton button = findViewById(checkedId);
            chosenTime = button.getText().toString();
        });

        // Get the selected number of persons
        personCountButtonGroup = findViewById(R.id.num_of_person_toggle_group);
        personCountButtonGroup.addOnButtonCheckedListener((MaterialButtonToggleGroup group, int checkedId, boolean isChecked) -> {
            MaterialButton button = findViewById(checkedId);
            numOfPersons = button.getText().toString();
        });
    }


    // save info in database
    // display the information in ReservationOverviewActivity
    // add swipe to delete
    public void bookRestaurant(View view) {
        if (chosenDate == null | chosenTime == null | numOfPersons == null) {
            Toast.makeText(this, "You have not selected the date or time ",
                    Toast.LENGTH_LONG).show();
        } else {
            ArrayList<String> reservationInformation = new ArrayList<>();
            reservationInformation.add(restaurantName);
            reservationInformation.add(restaurantImage);
            reservationInformation.add(chosenDate);
            reservationInformation.add(chosenTime);
            reservationInformation.add(numOfPersons);

            Toast.makeText(this, restaurantName + " has been reserved",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, ReservationOverviewActivity.class);
            intent.putStringArrayListExtra(RESERVATION_REPLY, reservationInformation);
            startActivity(intent);
        }

    }

}
