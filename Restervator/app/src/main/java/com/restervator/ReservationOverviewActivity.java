package com.restervator;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.restervator.adapter.ReservationAdapter;
import com.restervator.database.Reservation;
import com.restervator.database.ReservationViewModel;

import java.util.List;

public class ReservationOverviewActivity extends AppCompatActivity {

    private ReservationViewModel reservationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_overview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        final ReservationAdapter adapter = new ReservationAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reservationViewModel = ViewModelProviders.of(this).get(ReservationViewModel.class);

        reservationViewModel.getAllReservations().observe(this, new Observer<List<Reservation>>() {
            @Override
            public void onChanged(@Nullable final List<Reservation> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setReservations(words);
            }
        });
    }
}
