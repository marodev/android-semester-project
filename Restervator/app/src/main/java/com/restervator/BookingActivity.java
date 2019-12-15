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

import java.util.Calendar;
import java.util.Date;

import static com.restervator.RestaurantActivity.RESTAURANT_REPLY;
import static mil.nga.tiff.FieldTagType.DateTime;

public class BookingActivity extends AppCompatActivity {

    String restaurantName = "";
    CalendarView calendarView;
    MaterialButtonToggleGroup timeButtonGroup;
    MaterialButtonToggleGroup personCountButtonGroup;
    String chosenDate;
    String chosenTime;
    String numOfPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        restaurantName = getIntent().getExtras().getString(RESTAURANT_REPLY);

        TextView restaurantNameView = findViewById(R.id.bookingRestaurantName);
        restaurantNameView.setText(restaurantName);


        //Get the selected date whenever a new date is picked in the calendar.
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((CalendarView view, int year, int month,
                                            int dayOfMonth) ->
                chosenDate = dayOfMonth +" - " + (month+1) + " - " + year);


        //Get the selected time
        timeButtonGroup = findViewById(R.id.lunch_time_toggle_group);
        timeButtonGroup.addOnButtonCheckedListener((MaterialButtonToggleGroup group , int checkedId, boolean isChecked) -> {
                    MaterialButton button = findViewById(checkedId);
                    chosenTime = button.getText().toString();
                });

        //Get the selected number of persons
        personCountButtonGroup = findViewById(R.id.num_of_person_toggle_group);
        personCountButtonGroup.addOnButtonCheckedListener((MaterialButtonToggleGroup group , int checkedId, boolean isChecked) -> {
                    MaterialButton button = findViewById(checkedId);
                    numOfPersons = button.getText().toString();
                });
    }


    //save info in database
    //display the information in ReservationOverviewActivity
    //add swipe to delete

    public void bookRestaurant(View view) {

        Toast.makeText(this, restaurantName +chosenDate + " "+ chosenTime+ " "+ numOfPersons+" has been reserved",
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, ReservationOverviewActivity.class);
        startActivity(intent);
    }

}
