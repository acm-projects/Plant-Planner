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
        ArrayList<plant> plantsList = new ArrayList<plant>();
        plantsList.add(new plant("sunflower", "whatever scientific sunflower name is", R.drawable.greenleaf));
        plantsList.add(new plant("rose", "scientific name here", R.drawable.greenleaf));
        plantsList.add(new plant("European Silver Fir", "Abies alba", R.drawable.greenleaf));
        plantsList.add(new plant("cactus", "pokey thing", R.drawable.greenleaf));
        plantsList.add(new plant("palm tree", "scientific palm tree", R.drawable.greenleaf));
        plantsList.add(new plant("does this scroll", "pls work", R.drawable.greenleaf));
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