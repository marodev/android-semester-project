package com.restervator.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.restervator.R;
import com.restervator.RestaurantActivity;
import com.restervator.model.domain.Restaurant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    public static final String EXTRA_REPLY =
            "com.restervator.adapter.extra.REPLY";

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.firstimage)
        ImageView firstImage;
        @BindView(R.id.name)
        TextView nameTextView;
//        @BindView(R.id.description)
//        TextView descriptionTextView;


        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    private ArrayList<Restaurant> mRestaurants;
    private AdapterView.OnItemClickListener onItemClickListener;
    private Context context;


    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.context = context;
        this.mRestaurants = restaurants;
    }

    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View RestaurantView = inflater.inflate(R.layout.recyclerview_item, parent,false);

        return new ViewHolder(RestaurantView);
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.ViewHolder viewHolder, int position) {
        Restaurant restaurant = mRestaurants.get(position);

        TextView textView = viewHolder.nameTextView;
        textView.setText(restaurant.getName());

        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RestaurantActivity.class);
            intent.putExtra(EXTRA_REPLY, restaurant.getName());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = (AdapterView.OnItemClickListener) onItemClickListener;
    }


}

