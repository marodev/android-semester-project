package com.restervator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.restervator.adapter.ReservationAdapter;
import com.restervator.database.Reservation;
import com.restervator.database.ReservationViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.restervator.BookingActivity.RESERVATION_REPLY;

public class ReservationOverviewActivity extends AppCompatActivity {

    private ReservationViewModel reservationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_overview);

        //initialises the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        final ReservationAdapter adapter = new ReservationAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //initialise the reservationViewModel
        reservationViewModel = ViewModelProviders.of(this).get(ReservationViewModel.class);

        if (getIntent().hasExtra(RESERVATION_REPLY)) {
            //get reservation data from intent and store it in the database.
            ArrayList<String> reservationInformation = getIntent().getStringArrayListExtra(RESERVATION_REPLY);
            Reservation reservation = new Reservation(reservationInformation.get(0),
                    reservationInformation.get(1),
                    reservationInformation.get(2),
                    reservationInformation.get(3),
                    1);
            reservationViewModel.insert(reservation);
        }

        //        Picasso.get()
//                .load("https://media-cdn.tripadvisor.com/media/photo-s/0e/cc/0a/dc/restaurant-chocolat.jpg")
//                .into(restaurantImageView);

        reservationViewModel.getAllReservations().observe(this, (@Nullable final List<Reservation> reservations) ->
                adapter.setReservations(reservations) );


        ///Functionality to swipe an item to delete it
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
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
                        Reservation myWord = adapter.getReservationAtPosition(position);
                        Toast.makeText(ReservationOverviewActivity.this, "Deleting " +
                                myWord.getName(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        reservationViewModel.deleteWord(myWord);
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
