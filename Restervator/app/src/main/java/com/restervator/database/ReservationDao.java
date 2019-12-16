package com.restervator.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReservationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Reservation reservation);

    @Query("DELETE FROM reservation_table")
    void deleteAll();


    @Query("SELECT * from reservation_table")
    LiveData<List<Reservation>> getAllReservations();

    @Query("SELECT * from reservation_table LIMIT 1")
    Reservation[] getAnyWord();

    @Delete
    void deleteReservation(Reservation reservation);
}
