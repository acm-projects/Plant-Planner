package com.example.plantplanner;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.BuildConfig;
import com.android.volley.RequestQueue;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.BuildConfig;
import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class SearchPageActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private ArrayList<Plant> plantsList;
    private customAdapter adapter;
    private SearchView searchView;
    private ImageButton userBtn;
    private ImageButton calendarBtn;
    private ImageButton curPlantsBtn;
    private ImageButton helpBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_plant_page);

        searchView = findViewById(R.id.plantSearch);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = findViewById(R.id.searchPlantsRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //new array list
        plantsList = new ArrayList<Plant>();
        plantsList.add(new Plant("sunflower", "whatever scientific sunflower name is", 123, 1));
        plantsList.add(new Plant("rose", "scientific name here", 123, 2));
        plantsList.add(new Plant("European Silver Fir", "Abies alba", 123, 3));
        plantsList.add(new Plant("cactus", "pokey thing", 123, 4));
        plantsList.add(new Plant("palm tree", "palm tree but sciency", 123, 5));
        plantsList.add(new Plant("does this scroll", "pls work", 123, 6));
        //create adapter
        adapter = new customAdapter(this, plantsList);
        //create vertical list with linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //set layout manager and adapter to the recycler view
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);










        // Navigates to User Page
        userBtn = (ImageButton)findViewById(R.id.profileButton);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPageActivity.this, UserPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Calendar Page
        calendarBtn = (ImageButton)findViewById(R.id.calendarButton);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPageActivity.this, CalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Current Plants Page
        curPlantsBtn = (ImageButton)findViewById(R.id.plantButton);
        curPlantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPageActivity.this, CurrentPlantsActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to HowToUse Page
        helpBtn = (ImageButton)findViewById(R.id.helpButton);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchPageActivity.this, HowToUseActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void filterList(String text) {
        ArrayList<Plant> filteredList = new ArrayList<Plant>();
        for(Plant p : plantsList){
            if(p.getCommon_name().toLowerCase().contains(text.toLowerCase()))
                filteredList.add(p);
        }
        adapter.setFilteredList(filteredList);
    }
}
