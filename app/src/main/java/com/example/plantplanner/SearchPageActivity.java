package com.example.plantplanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchPageActivity extends AppCompatActivity implements RecyclerViewInterface {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_SCI_NAME = "sciName";
    private RecyclerView mRecyclerView;
    private ArrayList<Plant> mPlantList = new ArrayList<>();
    private SearchView searchView;
    // Creating a SearchViewHolder
    private CustomAdapter.SearchViewHolder searchHolder;
    private TextView name, sciName;
    private ImageView plantImg;
    private RelativeLayout plantRelLayout;


    // Variables Used for Navigation
    private ImageButton userBtn;
    private ImageButton calendarBtn;
    private ImageButton curPlantsBtn;
    private ImageButton helpBtn;
    String url = "https://perenual.com/api/species-list?q=&watering=&key=sk-k9GS6539ce97aae8e2713";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_plant_page);


        mRecyclerView = findViewById(R.id.searchPlantsRV);
        searchView = findViewById(R.id.plantSearch);
        name = findViewById(R.id.idPlantCommonName);
        sciName = findViewById(R.id.idPlantSciName);
        plantImg = findViewById(R.id.idPlantImage);
        plantRelLayout = findViewById(R.id.cardId);

        CustomAdapter adapter = new CustomAdapter(this, mPlantList, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RequestQueue queue = Volley.newRequestQueue(SearchPageActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mRecyclerView.setVisibility(View.VISIBLE);

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 1; i < jsonArray.length(); i++)
                    {
                        JSONObject data = jsonArray.getJSONObject(i);

                        String plantCommonName = data.getString("common_name");
                        String plantSciName = data.getString("scientific_name");
                        int plantID = data.getInt("id");
                        String wateringSchedule = data.getString("watering");
                        String imageUrl = "";
                        try {
                            JSONObject default_image = data.getJSONObject("default_image");
                            imageUrl = default_image != null ? default_image.getString("regular_url") : "";
                        } catch(Exception e) {}
                        mPlantList.add(new Plant(plantCommonName, plantSciName, imageUrl, plantID, wateringSchedule));
                    }
                    mRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchPageActivity.this, "Fail to get data...", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonObjectRequest);


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
