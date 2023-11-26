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

    private ArrayList<Plant> plantsList;
    private customAdapterOld adapter;
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

        // Manages Search and Recycler View
        mRecyclerView = findViewById(R.id.searchPlantsRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        mRecyclerView = findViewById(R.id.searchPlantsRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPlantList = new ArrayList<>();

        mPlantList.add(new Plant("European Silver Fir", "Abies alba", "https:\\/\\/perenual.com\\/storage\\/species_image\\/1_abies_alba\\/regular\\/1536px-Abies_alba_SkalitC3A9.jpg", 1));
        mPlantList.add(new Plant("Pyramidalis Silver Fir", "Abies alba Pyramidalis", "https:\\/\\/perenual.com\\/storage\\/species_image\\/2_abies_alba_pyramidalis\\/regular\\/49255769768_df55596553_b.jpg", 2));
        mPlantList.add(new Plant("Golden Fullmoon Maple", "Acer japonicum Aureum", "https:\\/\\/perenual.com\\/storage\\/species_image\\/20_acer_japonicum_aureum\\/regular\\/2560px-Acer_shirasawanum_27Aureum27.jpg", 20));
        mPlantList.add(new Plant("Oregon Sunset Japanese Maple", "Acer palmatum Oregon Sunset", "https:\\/\\/perenual.com\\/storage\\/species_image\\/88_acer_palmatum_oregon_sunset\\/regular\\/autumn-leaves-leaves-autumn-the-leaves-deciduous-nature.jpg", 88));
        mPlantList.add(new Plant("Slenderleaf False Foxglove", "Agalinis tenuifolia", "https:\\/\\/perenual.com\\/storage\\/species_image\\/571_agalinis_tenuifolia\\/regular\\/51729887719_21b2944da8_b.jpg", 571));
        mPlantList.add(new Plant("Lily of the Nile", "Agapanthus Blue Yonder", "https:\\/\\/perenual.com\\/storage\\/species_image\\/578_agapanthus_rfdd_double_diamond\\/regular\\/51685545730_a49c37d107_b.jpg", 573));
        mPlantList.add(new Plant("Giant Hyssop", "Agastache Black Adder", "https:\\/\\/perenual.com\\/storage\\/species_image\\/582_agastache_black_adder\\/regular\\/20222503530_c6fa0ff0f0_b.jpg", 582));
        mPlantList.add(new Plant("Zebra Plant", "Calathea Zebrina", "https:\\/\\/perenual.com\\/storage\\/species_image\\/1471_calathea_zebrina\\/regular\\/24798559861_0c6830ba26_b.jpg", 1471));
        mPlantList.add(new Plant("Beautyberry", "Callicarpa americana", "https:\\/\\/perenual.com\\/storage\\/species_image\\/1478_callicarpa_americana\\/regular\\/51661452592_e28f0a0bbc_b.jpg", 1478));
        mPlantList.add(new Plant("Prairie Poppy Mallow", "Callirhoe alcaeoides Logan Calhoun", "https://mdc.mo.gov/sites/default/files/styles/gallery_main_image/public/2021-05/Prairie_Poppy_Mallow_side_7-3-09.jpg?itok=UIBbw9ud", 1493));
        mPlantList.add(new Plant("Dropwort", "Filipendula vulgaris Multiplex", "https:\\/\\/perenual.com\\/storage\\/species_image\\/2975_filipendula_vulgaris_multiplex\\/regular\\/9187614702_fdb7352983_b.jpg", 2975));
        mPlantList.add(new Plant("Aloe", "Aloe harlana", "https:\\/\\/perenual.com\\/storage\\/species_image\\/721_aloe_harlana\\/regular\\/5582077315_8d613454aa_b.jpg", 721, "21"));
        mPlantList.add(new Plant("Lily of the Incas", "Alstroemeria isabellana", "https:\\/\\/perenual.com\\/storage\\/species_image\\/738_alstroemeria_isabellana\\/regular\\/29065273245_e56206d0d9_b.jpg", 738, "6-8"));
        mPlantList.add(new Plant("Hardy Kiwi", "Actinidia arguta", "https:\\/\\/perenual.com\\/storage\\/species_image\\/534_actinidia_arguta\\/regular\\/8054888032_690ac1e6ae_b.jpg", 534, "2-4"));


        //create adapter
        adapter = new customAdapterOld(this, mPlantList);
        //create vertical list with linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //set layout manager and adapter to the recycler view
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);


        //mRequestQueue = Volley.newRequestQueue(this);
        //parseJSON();


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
        for (Plant p : mPlantList) {
            if (p.getCommon_name().toLowerCase().contains(text.toLowerCase()))
                filteredList.add(p);
        }
        adapter.setFilteredList(filteredList);
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
