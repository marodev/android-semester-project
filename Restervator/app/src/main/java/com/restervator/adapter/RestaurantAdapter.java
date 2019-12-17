package com.restervator.adapter;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.restervator.R;
import com.restervator.RestaurantActivity;
import com.restervator.location.LocationFetcher;
import com.restervator.model.domain.Restaurant;
import com.restervator.utils.ImageUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    public static final String EXTRA_REPLY = "com.restervator.adapter.extra.REPLY";

    private ArrayList<Restaurant> mRestaurants;
    private Context context;


    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.context = context;
        this.mRestaurants = restaurants;
    }

    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View RestaurantView = inflater.inflate(R.layout.recyclerview_item, parent, false);

        return new ViewHolder(RestaurantView);
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.ViewHolder viewHolder, int position) {
        // add text to the view elements
        Restaurant restaurant = mRestaurants.get(position);
        ImageView thumbnailView = viewHolder.firstImage;
        ImageUtil.loadImage(restaurant.getThumbnailUrl(), thumbnailView);

        TextView textView = viewHolder.nameTextView;
        textView.setText(restaurant.getName());

        TextView descriptionTextView = viewHolder.descriptionTextView;
        descriptionTextView.setText(restaurant.getFullAddress());

        RatingBar ratingBar = viewHolder.ratingBar;
        ratingBar.setNumStars((int) Math.round(restaurant.getAvgUserRating()));

        TextView avgPriceView = viewHolder.restaurantAvgPriceView;
        String avgPrice = restaurant.getCurrency() + " " + restaurant.getAvgPriceForTwo();
        avgPriceView.setText(avgPrice);

        TextView distanceView = viewHolder.distanceToUser;
        Location restaurantLocation = new Location("RandomProvider");
        restaurantLocation.setLatitude(restaurant.getLatitude());
        restaurantLocation.setLongitude(restaurant.getLongitude());
        // calculate distance to user and display as text
        distanceView.setText(LocationFetcher.getDistanceToLocation(restaurantLocation));

        // create array with information to pass to the next activity
        ArrayList<String> intentMessage = new ArrayList<>();
        intentMessage.add(restaurant.getName());
        intentMessage.add(restaurant.getFullAddress());
        intentMessage.add(restaurant.getPhoneNumber());
        intentMessage.add(restaurant.getThumbnailUrl());
        intentMessage.add(String.valueOf(restaurant.getLatitude()));
        intentMessage.add(String.valueOf(restaurant.getLongitude()));

        // set item click listener to go to the next activity
        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RestaurantActivity.class);
            intent.putStringArrayListExtra(EXTRA_REPLY, intentMessage);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // inject views into the layout
        @BindView(R.id.firstimage)
        ImageView firstImage;
        @BindView(R.id.name)
        TextView nameTextView;
        @BindView(R.id.description)
        TextView descriptionTextView;
        @BindView(R.id.rating)
        RatingBar ratingBar;
        @BindView(R.id.restaurantAvgPrice)
        TextView restaurantAvgPriceView;
        @BindView(R.id.distanceToUser)
        TextView distanceToUser;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

