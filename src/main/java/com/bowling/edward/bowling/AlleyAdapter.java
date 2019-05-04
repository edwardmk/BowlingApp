package com.bowling.edward.bowling;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AlleyAdapter extends RecyclerView.Adapter<AlleyAdapter.MyHolder> {
    List<Alley> alleys;

    public AlleyAdapter(List<Alley> list) {
        this.alleys = list;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name,location, rating;

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameT);
            location = itemView.findViewById(R.id.locationT);
            rating = itemView.findViewById(R.id.ratingT);
        }
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alley_item,null);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Alley dataModel = alleys.get(position);
        holder.name.setText(dataModel.getName());
        holder.location.setText(dataModel.getLocation());
        String ratingString = String.valueOf(dataModel.getRating());
        holder.rating.setText(ratingString + " / 5");
    }

    @Override
    public int getItemCount() {
        return alleys.size();
    }
}