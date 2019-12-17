package com.restervator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.restervator.adapter.ReservationAdapter;
import com.restervator.database.Reservation;
import com.restervator.database.ReservationViewModel;

import java.util.ArrayList;

import static com.restervator.BookingActivity.RESERVATION_REPLY;

public class ReservationOverviewActivity extends AppCompatActivity {

    private ReservationViewModel reservationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_overview);

        // initialises the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        final ReservationAdapter adapter = new ReservationAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // initialise the reservationViewModel
        reservationViewModel = ViewModelProviders.of(this).get(ReservationViewModel.class);

        if (getIntent().hasExtra(RESERVATION_REPLY)) {
            // get reservation data from intent and store it in the database.
            ArrayList<String> reservationInformation = getIntent().getStringArrayListExtra(RESERVATION_REPLY);
            int numberOfPerson;//= Integer.valueOf(reservationInformation.get(4).split("-")[1]);
            if (reservationInformation.get(4).equals("9+")) {
                numberOfPerson = 9;
            } else {
                numberOfPerson = Integer.valueOf(reservationInformation.get(4).split("-")[1]);
            }
            Reservation reservation = new Reservation(reservationInformation.get(0),
                    reservationInformation.get(1),
                    reservationInformation.get(2),
                    reservationInformation.get(3),
                    numberOfPerson);
            reservationViewModel.insert(reservation);
        }

        reservationViewModel.getAllReservations().observe(this, adapter::setReservations);


        // Functionality to swipe an item to delete it
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Reservation reservation = adapter.getReservationAtPosition(position);
                        Toast.makeText(ReservationOverviewActivity.this, "Deleting " +
                                reservation.getName(), Toast.LENGTH_LONG).show();

                        // Delete the reservation
                        reservationViewModel.deleteReservation(reservation);
                    }
                });

        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onBackPressed() {
        // go back to MainActivity - Restaurants
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
