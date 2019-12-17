package com.restervator.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.restervator.R;
import com.restervator.database.Reservation;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
                Reservation reservation = reservations.get(position);

                TextView textView = holder.nameTextView;
                textView.setText(reservation.getName());

                ImageView firstImage = holder.firstImage;
                Picasso.get()
                        .load(reservation.getImage())
                        .into(firstImage);

                TextView descriptionView = holder.descriptionTextView;
                descriptionView.setText("Reservation for "+reservation.getNumOfPersons() +
                        " Person(s) on "+ reservation.getDate() +", "+reservation.getTime() );
            } else {
                // Covers the case of data not being ready yet.
                holder.nameTextView.setText("No Reservation");
            }
        }

        public void setReservations(List<Reservation> reservations){
            this.reservations = reservations;
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
            @BindView(R.id.firstimage)
            ImageView firstImage;
            @BindView(R.id.name)
            TextView nameTextView;
            @BindView(R.id.description)
            TextView descriptionTextView;

            private ReservationViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

}
