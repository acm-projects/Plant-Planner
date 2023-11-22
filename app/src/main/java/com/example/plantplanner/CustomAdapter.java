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
import java.util.List;

import com.squareup.picasso.Picasso;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.SearchViewHolder> {
    private Context mContext;
    private ArrayList<Plant> plantList;
    private static OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setFilteredList(ArrayList<Plant> filteredList)
    {
        plantList = filteredList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public CustomAdapter(Context context, ArrayList<Plant> arrayList) {
        mContext = context;
        plantList = arrayList;
    }

    @NonNull
    @Override
    public CustomAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.SearchViewHolder holder, int position) {
        Plant plant = plantList.get(position);

        String imageUrl = plantList.get(position).getPlant_image();
        String name = plantList.get(position).getCommon_name();
        String sciName = plantList.get(position).getScientific_name();

        holder.plantCommonName.setText(name);
        holder.plantSciName.setText(sciName);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.plantImage);
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }


    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        private final ImageView plantImage;
        private final TextView plantCommonName;
        private final TextView plantSciName;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            plantImage = (ImageView) itemView.findViewById(R.id.idPlantImage);
            plantCommonName = (TextView) itemView.findViewById(R.id.idPlantCommonName);
            plantSciName = (TextView) itemView.findViewById(R.id.idPlantSciName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }
}
