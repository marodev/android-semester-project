package com.restervator.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Reservation.class}, version = 1, exportSchema = false)
public abstract class ReservationRoomDatabase extends RoomDatabase {

    public abstract ReservationDao reservationDao();
    private static ReservationRoomDatabase INSTANCE;

    static ReservationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReservationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ReservationRoomDatabase.class, "reservation_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
