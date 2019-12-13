package com.restervator;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.restervator.model.RestaurantList;

import java.util.ArrayList;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

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

    private ArrayList<RestaurantList> mRestaurants;
    private AdapterView.OnItemClickListener onItemClickListener;
    private Context context;



    public ListAdapter(Context context, ArrayList<RestaurantList> Restaurants){
        this.context = context;
        this.mRestaurants = Restaurants;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View RestaurantView = inflater.inflate(R.layout.recyclerview_item, parent,false);

        ViewHolder viewHolder = new ViewHolder(RestaurantView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder viewHolder,int position){
        RestaurantList restaurant = mRestaurants.get(position);

        TextView textView = viewHolder.nameTextView;
        textView.setText(restaurant.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RestaurantActivity.class);
                context.startActivity(intent);
            }
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

