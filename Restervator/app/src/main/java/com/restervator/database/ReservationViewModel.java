package com.restervator.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ReservationViewModel extends AndroidViewModel {
    private ReservationRepository mRepository;
    private LiveData<List<Reservation>> allReservations;

    public ReservationViewModel (Application application) {
        super(application);
        mRepository = new ReservationRepository(application);
        allReservations = mRepository.getAllReservations();
    }

    LiveData<List<Reservation>> getAllReservations() { return allReservations; }

    public void insert(Reservation reservation) {   mRepository.insert(reservation); }
}
