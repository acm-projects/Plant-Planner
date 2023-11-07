package com.example.plantplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;

public class IndivPlantActivity extends AppCompatActivity {

    Button add, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indiv_plant_page);

        add = (Button) findViewById(R.id.addButton);
        delete = (Button) findViewById(R.id.deleteButton);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(IndivPlantActivity.this, "Added plant", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(IndivPlantActivity.this, "Deleted plant", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
