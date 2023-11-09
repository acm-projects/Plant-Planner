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
public class calendarAdapter extends RecyclerView.Adapter<calendarAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<Event> eventsList;

    public boolean clickable = false;

    // Constructor
    public calendarAdapter(Context context, ArrayList<Event> eventArrayList) {
        this.context = context;
        this.eventsList = eventArrayList;
    }

    @NonNull
    @Override
    public calendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull calendarAdapter.ViewHolder holder, int position) {
        Event e = eventsList.get(position);
        holder.eventNameTV.setText(e.getEventDescription());
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview for regular card
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView eventNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameTV = itemView.findViewById(R.id.idEventName);
        }


    }
}
