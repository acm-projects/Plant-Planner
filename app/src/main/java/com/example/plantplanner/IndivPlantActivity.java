package com.example.plantplanner;

import static com.example.plantplanner.SearchPageActivity.EXTRA_NAME;
import static com.example.plantplanner.SearchPageActivity.EXTRA_SCI_NAME;
import static com.example.plantplanner.SearchPageActivity.EXTRA_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;

import com.squareup.picasso.Picasso;


public class IndivPlantActivity extends AppCompatActivity {

    private Button add, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indiv_plant_page);

        add = (Button) findViewById(R.id.addButton);
        delete = (Button) findViewById(R.id.deleteButton);


        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String plantComName = intent.getStringExtra(EXTRA_NAME);
        String plantSciName = intent.getStringExtra(EXTRA_SCI_NAME);

        ImageView imageView = findViewById(R.id.book);
        TextView textViewName = findViewById(R.id.plantName);
        TextView textViewSciName = findViewById(R.id.plantSciName);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewName.setText(plantComName);
        textViewSciName.setText(plantSciName);


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
