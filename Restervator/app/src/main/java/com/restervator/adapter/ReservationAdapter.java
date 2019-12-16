package com.restervator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.restervator.R;
import com.restervator.database.Reservation;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

        private final LayoutInflater mInflater;
        private List<Reservation> reservations; // Cached copy of words

        public ReservationAdapter(Context context) { mInflater = LayoutInflater.from(context); }

        @Override
        public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new ReservationViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ReservationViewHolder holder, int position) {
            if (reservations != null) {
                Reservation current = reservations.get(position);
                holder.restaurantNameView.setText(current.getName());
            } else {
                // Covers the case of data not being ready yet.
                holder.restaurantNameView.setText("No Reservation");
            }
        }

        public void setReservations(List<Reservation> words){
            reservations = words;
            notifyDataSetChanged();
        }

        // getItemCount() is called many times, and when it is first called,
        // reservations has not been updated (means initially, it's null, and we can't return null).
        @Override
        public int getItemCount() {
            if (reservations != null)
                return reservations.size();
            else return 0;
        }

         public Reservation getReservationAtPosition (int position) {
             return reservations.get(position);
        }


        class ReservationViewHolder extends RecyclerView.ViewHolder {
            private final TextView restaurantNameView;

            private ReservationViewHolder(View itemView) {
                super(itemView);
                restaurantNameView = itemView.findViewById(R.id.name);
            }
        }

}
