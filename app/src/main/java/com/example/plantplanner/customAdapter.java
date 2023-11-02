package com.example.plantplanner;

import android.content.Context;
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
    private final ArrayList<Plant> plantArrayList;

    // Constructor
    public customAdapter(Context context, ArrayList<Plant> plantArrayList) {
        this.context = context;
        this.plantArrayList = plantArrayList;
    }

    @NonNull
    @Override
    public customAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        Plant p = plantArrayList.get(position);
        holder.plantCommonNameTV.setText(p.getCommon_name());
        holder.plantScientificNameTV.setText("" + p.getScientific_name());
        holder.plantIV.setImageResource(p.getPlant_image());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return plantArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView plantIV;
        private final TextView plantCommonNameTV;
        private final TextView plantScientificNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            plantIV = itemView.findViewById(R.id.idPlantImage);
            plantCommonNameTV = itemView.findViewById(R.id.idPlantImage);
            plantScientificNameTV = itemView.findViewById(R.id.idPlantSciName);
        }
    }
}
