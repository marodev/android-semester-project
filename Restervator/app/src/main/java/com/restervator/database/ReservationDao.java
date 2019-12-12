package com.restervator.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReservationDao {

    @Insert
    void insert(Reservation reservation);

    @Query("DELETE FROM reservation_table")
    void deleteAll();


    @Query("SELECT * from reservation_table ORDER BY name ASC")
    LiveData<List<Reservation>> getAllReservations();
}
