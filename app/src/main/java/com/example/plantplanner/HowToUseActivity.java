package com.example.plantplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HowToUseActivity extends AppCompatActivity {

    private ImageButton userBtn;
    private ImageButton searchBtn;
    private ImageButton calendarBtn;
    private ImageButton curPlantsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_use_page);

        // Navigates to User Page
        userBtn = (ImageButton)findViewById(R.id.profileButton);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HowToUseActivity.this, UserPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Search Plants Page
        searchBtn = (ImageButton)findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HowToUseActivity.this, SearchPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Calendar Page
        calendarBtn = (ImageButton)findViewById(R.id.calendarButton);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HowToUseActivity.this, CalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Current Plants Page
        curPlantsBtn = (ImageButton)findViewById(R.id.plantButton);
        curPlantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HowToUseActivity.this, CurrentPlantsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}