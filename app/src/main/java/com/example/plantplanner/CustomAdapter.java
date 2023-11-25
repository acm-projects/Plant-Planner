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
    private final RecyclerViewInterface recyclerViewInterface;

    private Context mContext;
    private ArrayList<Plant> plantList = new ArrayList<>();
    //private static OnItemClickListener mListener;


    public void setFilteredList(ArrayList<Plant> filteredList)
    {
        plantList = filteredList;
        notifyDataSetChanged();
    }


    public CustomAdapter(Context context, ArrayList<Plant> arrayList, RecyclerViewInterface recyclerViewInterface) {
        mContext = context;
        plantList = arrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public CustomAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new SearchViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.SearchViewHolder holder, int position) {
        Plant plant = plantList.get(position);

        String imageUrl = plantList.get(position).getPlant_image();
        String name = plantList.get(position).getCommon_name();
        String sciName = plantList.get(position).getScientific_name();

        holder.plantCommonName.setText(name);
        holder.plantSciName.setText(sciName);
        if (imageUrl != "")
        {
            Picasso.get().load(imageUrl).fit().centerInside().into(holder.plantImage);
        }
    }

    @Override
    public int getItemCount() {
        if (plantList == null)
        {
            return 0;
        }
        return plantList.size();
    }


    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        private final ImageView plantImage;
        private final TextView plantCommonName;
        private final TextView plantSciName;

        public SearchViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            plantImage = (ImageView) itemView.findViewById(R.id.idPlantImage);
            plantCommonName = (TextView) itemView.findViewById(R.id.idPlantCommonName);
            plantSciName = (TextView) itemView.findViewById(R.id.idPlantSciName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }

    }
}
