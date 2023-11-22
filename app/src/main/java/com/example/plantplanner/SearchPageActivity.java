package com.example.plantplanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
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
    private static final String TAG = "CHECKING CHECKING";


    private RecyclerView mRecyclerView;
    private CustomAdapter mCustomAdapter;
    private ArrayList<Plant> mPlantList;
    private RequestQueue mRequestQueue;
    private SearchView searchView;
    // Creating a SearchViewHolder
    private CustomAdapter.SearchViewHolder searchHolder;


    // Variables Used for Navigation
    private ImageButton userBtn;
    private ImageButton calendarBtn;
    private ImageButton curPlantsBtn;
    private ImageButton helpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_plant_page);

        searchView = findViewById(R.id.plantSearch);
        //searchView.clearFocus();
        // Manages Recycler View
        mRecyclerView = findViewById(R.id.searchPlantsRV);


        //Creates ArrayList of Plants and Tests w/ 1 plant
        mPlantList = new ArrayList<>();
        getPlantData();
        //mRequestQueue = Volley.newRequestQueue(this);
        //parseJSON();
        setAdapter();

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

    private void getPlantData() {
        mPlantList.add(new Plant("European Silver Fir", "Abies alba", "https://perenual.com/storage/species_image/1_abies_alba/regular/1536px-Abies_alba_SkalitC3A9.jpg", 1));
        mPlantList.add(new Plant("Pyramidalis Silver Fir", "Abies alba 'Pyramidalis", "https://perenual.com/storage/species_image/2_abies_alba_pyramidalis/regular/49255769768_df55596553_b.jpg", 2));
        mPlantList.add(new Plant("White Fir", "Abies concolor", "https://perenual.com/storage/species_image/3_abies_concolor/regular/52292935430_f4f3b22614_b.jpg", 3));
        mPlantList.add(new Plant("Candicans White Fir", "Abies concolor 'Candicans", "https://perenual.com/storage/species_image/4_abies_concolor_candicans/regular/49283844888_332c9e46f2_b.jpg", 4));
        mPlantList.add(new Plant("Fraser Fir", "Abies fraseri", "https://perenual.com/storage/species_image/5_abies_fraseri/regular/36843539702_e80fc436e0_b.jpg", 5));
        mPlantList.add(new Plant("Golden Korean Fir", "Abies koreana 'Aurea", "https://perenual.com/storage/species_image/6_abies_koreana_aurea/regular/49235570926_99ec10781d_b.jpg", 6));
        mPlantList.add(new Plant("Alpine Fir", "Abies lasiocarpa", "https://perenual.com/storage/species_image/7_abies_lasiocarpa/regular/51002756843_74fae3c2fa_b.jpg", 7));
        mPlantList.add(new Plant("Blue Spanish Fir", "Abies pinsapo 'Glauca", "https://perenual.com/storage/species_image/8_abies_pinsapo_glauca/regular/21657514018_c0d9fed9f4_b.jpg", 8));
        mPlantList.add(new Plant("Noble Fir", "Abies procera", "https://perenual.com/storage/species_image/9_abies_procera/regular/49107504112_6bd7effb8b_b.jpg", 9));
        mPlantList.add(new Plant("Johin Japanese Maple", "Acer 'Johin", "https://perenual.com/storage/species_image/10_acer_johin/regular/pexels-photo-2183508.jpg", 10));
    }


    private void setAdapter () {
        CustomAdapter adapter = new CustomAdapter(this.getApplicationContext(), mPlantList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }



    private void parseJSON() {
        String url = "https://perenual.com/api/species-list?q=&watering=&key=sk-k9GS6539ce97aae8e2713";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "onResponseEvent");
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 1; i < 6; i++)
                    {
                        JSONObject data = jsonArray.getJSONObject(i);

                        String plantCommonName = data.getString("common_name");
                        String plantSciName = data.getString("scientific_name");
                        int plantID = data.getInt("id");

                        JSONObject defaultImg = data.getJSONObject("default_image");
                        String imageUrl = defaultImg.getString("regular_url");

                        mPlantList.add(new Plant(plantCommonName, plantSciName, "https://www.thephotoargus.com/wp-content/uploads/2020/02/rosepic12.jpg", plantID));
                    }

                    //mCustomAdapter = new CustomAdapter(SearchPageActivity.this, mPlantList);
                    //mRecyclerView.setAdapter(mCustomAdapter);
                    //mRecyclerView.setOnClickListener((View.OnClickListener) SearchPageActivity.this);

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
