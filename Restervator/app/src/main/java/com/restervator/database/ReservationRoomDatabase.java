package com.restervator.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ReservationDao reservationDao;
        String[] reservations = {"Chinese", "no image", "12/3/2020", "6pm", "1-2"};

        PopulateDbAsync(ReservationRoomDatabase db) {
            reservationDao = db.reservationDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            if (reservationDao.getAnyWord().length < 1) {
                for (int i = 0; i <= reservations.length - 1; i++) {
                    Reservation reservation = new Reservation(reservations[0], reservations[1], reservations[2], reservations[3],5);
                    reservationDao.insert(reservation);
                }
            }
            return null;

        }
    }
}
