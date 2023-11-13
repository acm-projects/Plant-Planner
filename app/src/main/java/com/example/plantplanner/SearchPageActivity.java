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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.BuildConfig;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchPageActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_SCI_NAME = "sciName";

    private RecyclerView mRecyclerView;
    private CustomAdapter mCustomAdapter;
    private ArrayList<Plant> mPlantList;
    private RequestQueue mRequestQueue;

    private ImageButton userBtn;
    private ImageButton calendarBtn;
    private ImageButton curPlantsBtn;
    private ImageButton helpBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_plant_page);

        // Manages Search and Recycler View
        mRecyclerView = findViewById(R.id.searchPlantsRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPlantList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();


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



    private void parseJSON() {
        String url = "https://perenual.com/api/species-list?q=&watering=&key=sk-k9GS6539ce97aae8e2713";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject data = jsonArray.getJSONObject(i);

                        String plantCommonName = data.getString("common_name");
                        String imageUrl = data.getString("regular_url");
                        String plantSciName = data.getString("scientific_name");
                        int plantID = data.getInt("id");

                        mPlantList.add(new Plant(plantCommonName, plantSciName, imageUrl, plantID));
                    }

                    mCustomAdapter = new CustomAdapter(SearchPageActivity.this, mPlantList);
                    mRecyclerView.setAdapter(mCustomAdapter);
                    mRecyclerView.setOnClickListener((View.OnClickListener) SearchPageActivity.this);
                } catch (JSONException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, IndivPlantActivity.class);
        Plant clickedItem = mPlantList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getPlant_image());
        detailIntent.putExtra(EXTRA_NAME, clickedItem.getCommon_name());
        detailIntent.putExtra(EXTRA_SCI_NAME, clickedItem.getScientific_name());
        startActivity(detailIntent);
    }
}
