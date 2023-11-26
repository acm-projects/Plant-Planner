package com.example.plantplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class CurrentPlantsActivity extends AppCompatActivity {

    private ImageButton userBtn;
    private ImageButton searchBtn;
    private ImageButton calendarBtn;
    private ImageButton helpBtn;
    private ArrayList<Plant> mPlantList;
    private RequestQueue mRequestQueue;
    private CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_plants_page);
        //get recycler view
        RecyclerView mRecyclerView = findViewById(R.id.currentPlantsRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPlantList = new ArrayList<>();

        mPlantList.add(new Plant("Aloe", "Aloe harlana", "https:\\/\\/perenual.com\\/storage\\/species_image\\/721_aloe_harlana\\/regular\\/5582077315_8d613454aa_b.jpg", 721, "21"));
        mPlantList.add(new Plant("Lily of the Incas", "Alstroemeria isabellana", "https:\\/\\/perenual.com\\/storage\\/species_image\\/738_alstroemeria_isabellana\\/regular\\/29065273245_e56206d0d9_b.jpg", 738, "6-8"));
        mPlantList.add(new Plant("Hardy Kiwi", "Actinidia arguta", "https:\\/\\/perenual.com\\/storage\\/species_image\\/534_actinidia_arguta\\/regular\\/8054888032_690ac1e6ae_b.jpg", 534, "2-4"));
        mPlantList.add(new Plant("Lily of the Nile", "Agapanthus Blue Yonder", "https:\\/\\/perenual.com\\/storage\\/species_image\\/578_agapanthus_rfdd_double_diamond\\/regular\\/51685545730_a49c37d107_b.jpg", 573, "6-8"));


        //create adapter
        adapter = new CustomAdapter(this, mPlantList);
        //create vertical list with linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //set layout manager and adapter to the recycler view
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);



        // Navigates to User Page
        userBtn = (ImageButton)findViewById(R.id.profileButton);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentPlantsActivity.this, UserPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Search Plants Page
        searchBtn = (ImageButton)findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentPlantsActivity.this, SearchPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Calendar Page
        calendarBtn = (ImageButton)findViewById(R.id.calendarButton);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentPlantsActivity.this, CalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to HowToUse Page
        helpBtn = (ImageButton)findViewById(R.id.helpButton);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentPlantsActivity.this, HowToUseActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

