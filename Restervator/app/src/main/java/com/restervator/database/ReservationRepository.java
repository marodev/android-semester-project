package com.restervator.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ReservationRepository {
    private ReservationDao reservationDao;
    private LiveData<List<Reservation>> allReservations;


    ReservationRepository(Application application) {
        ReservationRoomDatabase db = ReservationRoomDatabase.getDatabase(application);
        reservationDao = db.reservationDao();
        allReservations = reservationDao.getAllReservations();
    }

    LiveData<List<Reservation>> getAllReservations() {
        return allReservations;
    }

    public void insert(Reservation reservation) {
        new insertAsyncTask(reservationDao).execute(reservation);
    }

    public void deleteAll() {
        new deleteAllReservationsAsyncTask(reservationDao).execute();
    }

    public void deleteReservation(Reservation reservation) {
        new deleteReservationAsyncTask(reservationDao).execute(reservation);
    }

    private static class insertAsyncTask extends AsyncTask<Reservation, Void, Void> {

        private ReservationDao mAsyncTaskDao;

        insertAsyncTask(ReservationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Reservation... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllReservationsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ReservationDao mAsyncTaskDao;

        deleteAllReservationsAsyncTask(ReservationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteReservationAsyncTask extends AsyncTask<Reservation, Void, Void> {
        private ReservationDao mAsyncTaskDao;

        deleteReservationAsyncTask(ReservationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Reservation... params) {
            mAsyncTaskDao.deleteReservation(params[0]);
            return null;
        }
    }
}
