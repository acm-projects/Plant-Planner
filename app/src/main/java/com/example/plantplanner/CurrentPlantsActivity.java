package com.example.plantplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentPlantsActivity extends AppCompatActivity {

    private ImageButton userBtn;
    private ImageButton searchBtn;
    private ImageButton calendarBtn;
    private ImageButton helpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_plants_page);
        //get recycler view
        RecyclerView plantsRV = findViewById(R.id.currentPlantsRV);
        //new array list
        ArrayList<Plant> plantsList = new ArrayList<Plant>();
        plantsList.add(new Plant("sunflower", "whatever scientific sunflower name is", 123, 1));
        plantsList.add(new Plant("rose", "scientific name here", 123, 2));
        plantsList.add(new Plant("European Silver Fir", "Abies alba", 123, 3));
        plantsList.add(new Plant("cactus", "pokey thing", 123, 4));
        plantsList.add(new Plant("palm tree", "scientific palm tree", 123, 5));
        plantsList.add(new Plant("does this scroll", "pls work", 123, 6));
        //create adapter
        customAdapter adapter = new customAdapter(this, plantsList);
        //create vertical list with linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //set layout manager and adapter to the recycler view
        plantsRV.setLayoutManager(linearLayoutManager);
        plantsRV.setAdapter(adapter);




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

