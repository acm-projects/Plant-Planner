package com.example.plantplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class customAdapter extends RecyclerView.Adapter<customAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<Plant> plantsList;

    public boolean clickable = false;

    // Constructor
    public customAdapter(Context context, ArrayList<Plant> plantArrayList) {
        this.context = context;
        this.plantsList = plantArrayList;
    }

    public void setFilteredList(ArrayList<Plant> filteredList){
        this.plantsList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (plantsList.size() == 0 && context instanceof SearchPageActivity){//empty filtered list for search page
            clickable = false;
            return 0;
        }
        else if(plantsList.size() == 0){//empty plant list in current plants
            clickable = false;
            return 1;
        }
        else {
            clickable = true;
            return 2; //has plants
        }
    }


    @NonNull
    @Override
    public customAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        if (viewType == 0) {//no found plants for search
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plants_not_found_layout, parent, false);
            return new ViewHolder(view, 0);
        } else if(viewType == 1){//no plants found in current plants
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_current_plants, parent, false);
            return new ViewHolder(view, 1);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
            return new ViewHolder(view, 2);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull customAdapter.ViewHolder holder, int position) {
        if(getItemViewType(position) == 2){
            // to set data to textview and imageview of each card layout
            Plant p = plantsList.get(position);
            holder.plantCommonNameTV.setText(p.getCommon_name());
            holder.plantScientificNameTV.setText("" + p.getScientific_name());
            //holder.plantIV.setImageResource(p.getPlant_image());
        }
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        if(plantsList.isEmpty()){ return 1; }//to display card, otherwise getView wont be called
        return plantsList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview for regular card
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView plantIV;
        private final TextView plantCommonNameTV;
        private final TextView plantScientificNameTV;

        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            plantIV = itemView.findViewById(R.id.idPlantImage);
            plantCommonNameTV = itemView.findViewById(R.id.idPlantCommonName);
            plantScientificNameTV = itemView.findViewById(R.id.idPlantSciName);

            if(viewType == 2){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemView.getContext().startActivity(new Intent(itemView.getContext(), IndivPlantActivity.class));
                    }
                });
            }
        }


    }
}
