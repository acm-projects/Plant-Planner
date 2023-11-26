package com.example.plantplanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;

public class CalendarActivity extends AppCompatActivity {

    private ImageButton userBtn;
    private ImageButton searchBtn;
    private ImageButton curPlantsBtn;
    private ImageButton helpBtn;

    Calendar calendar;
    CalendarView calendarV;
    HashMap<String, ArrayList<Event>> eventMap = new HashMap<String, ArrayList<Event>>();

    ArrayList<Event> adapterList = new ArrayList<Event>();
    RecyclerView recyclerV;
    calendarAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    TextView plantsToWater;
    final String msgNoPlants = "No Plants to Water Today!";
    final String msgYesPlants = "Plants to Water Today:";

    int iterationsAhead = 4;//how many waters to create events for


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_schedule);


        // Calendar Functions //

        setContentView(R.layout.user_schedule);

        calendarV = (CalendarView)findViewById(R.id.calendar);
        calendar = Calendar.getInstance();

        calendar.setFirstDayOfWeek(1); //sets first day of week to sunday

        plantsToWater = (TextView)findViewById(R.id.waterTodayTextMsg);

        //get recycler view
        recyclerV = findViewById(R.id.idrecycle);
        //create adapter
        adapter = new calendarAdapter(this, adapterList);
        //create vertical list with linear layout manager
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //set layout manager and adapter to the recycler view
        recyclerV.setLayoutManager(linearLayoutManager);
        recyclerV.setAdapter(adapter);
        //new array
        Plant[] plantsList = new Plant[8];
        plantsList[0] = new Plant("Aloe", "Aloe harlana", "https:\\/\\/perenual.com\\/storage\\/species_image\\/721_aloe_harlana\\/regular\\/5582077315_8d613454aa_b.jpg", 721, "21");
        plantsList[1] = new Plant("Lily of the Incas", "Alstroemeria isabellana", "https:\\/\\/perenual.com\\/storage\\/species_image\\/738_alstroemeria_isabellana\\/regular\\/29065273245_e56206d0d9_b.jpg", 738, "6-8");
        plantsList[2] = new Plant("Hardy Kiwi", "Actinidia arguta", "https:\\/\\/perenual.com\\/storage\\/species_image\\/534_actinidia_arguta\\/regular\\/8054888032_690ac1e6ae_b.jpg", 534, "2-4");
        plantsList[3] = new Plant("Lily of the Nile", "Agapanthus Blue Yonder", "https:\\/\\/perenual.com\\/storage\\/species_image\\/578_agapanthus_rfdd_double_diamond\\/regular\\/51685545730_a49c37d107_b.jpg", 573, "6-8");


        //date of last water
        String[] dateLastWatered = new String[8];//in YYYY-MM-DD format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = sdf.format(calendar.getTime());
        Log.d("CUR_DATE", curDate);
        for(int i = 0; i < 8; i++){
            if(plantsList[i] != null){
                dateLastWatered[i] = curDate;
            }
        }

        //Event eTest = new Event("pls work", 2023, 11, 30);
        //Toast.makeText(getApplicationContext(), eTest.getDate(), Toast.LENGTH_SHORT).show();
        ArrayList<Event> pls = new ArrayList<Event>();
        for(int i = 0; i < 8; i++){
            if(plantsList[i] != null){
                int y = Integer.parseInt(dateLastWatered[i].substring(0, 4));
                int m = Integer.parseInt(dateLastWatered[i].substring(5, 7));
                int d = Integer.parseInt(dateLastWatered[i].substring(8));
                for(int n = 0; n < iterationsAhead; n++){
                    Event e = new Event(plantsList[i]);
                    int freq = getWaterFrequencyInt(plantsList[i]);
                    e.setDate(y, m, d+(n*freq));
                    Log.d("PLEASE", e.getDate());
                    pls.add(e);
                }
            }
        }
        /*for(Plant p : plantsList){
            if(p != null){
                for(int i = 0; i < iterationsAhead; i++){
                    Event e = new Event(p);
                    int year =
                    e.setDate();
                }
                Event e = new Event(p);
                pls.add(e);
            }
        }*/
        for(Event e : pls){
            String date = e.getDate();
            ArrayList<Event> eventList = eventMap.get(date);
            if(eventList == null){
                eventList = new ArrayList<Event>();
                eventList.add(e);
                eventMap.put(date, eventList);
            } else {
                eventList.add(e);
            }
        }
        int y = Integer.parseInt(curDate.substring(0, 4));
        int m = Integer.parseInt(curDate.substring(5, 7));
        int d = Integer.parseInt(curDate.substring(8));
        displayEvents(y, m, d);

        calendarV.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            // get the value of DAYS, MONTH, YEARS
            public void onSelectedDayChange(@NonNull CalendarView calendarV, int year, int month, int day){
                String msg = "Selected date Month: " + (month+1)  + " Day : " + (day) + " Year " + (year);
                //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                displayEvents(year, month+1, day);
            }
        });

        /*
        public void changeDateColor(Date date, int color){

        }

         */


        // Navigates to User Page
        userBtn = (ImageButton) findViewById(R.id.profileButton);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, UserPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Search Plants Page
        searchBtn = (ImageButton) findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, SearchPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Current Plants Page
        curPlantsBtn = (ImageButton) findViewById(R.id.plantButton);
        curPlantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, CurrentPlantsActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to HowToUse Page
        helpBtn = (ImageButton) findViewById(R.id.helpButton);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, HowToUseActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    //calendar functions
    public void setDate(int year, int month, int day){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        calendarV.setDate(milli);
    }

    public void displayEvents(int year, int month, int day){
        String date = year + "-" + month + "-" + day;
        //Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
        ArrayList<Event> events = eventMap.get(date);
        if(events == null){
            events = new ArrayList<Event>();
        }
        if(events.isEmpty()){
            plantsToWater.setText(msgNoPlants);
        } else {
            plantsToWater.setText(msgYesPlants);
        }
        adapterList.clear();
        adapterList.addAll(events);
        adapter.notifyDataSetChanged();
    }

    public int getWaterFrequencyInt(Plant p){//return water frequency for plant p in days
        String w = p.getWaterFrequency();
        int intFreq = 0;
        if(w.contains("-")){//water frequency is a range ex: 5-7
            int indexDash = w.indexOf("-");
            int bottom = Integer.parseInt(w.substring(0, indexDash));
            int top = Integer.parseInt(w.substring(indexDash+1));
            intFreq = (int)(bottom+top)/2;//average of the end points
        }  else {//single number
            intFreq = Integer.parseInt(w);
        }
        return intFreq;
    }

}

/*
package com.example.plantplanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.Objects;

public class CalendarActivity extends AppCompatActivity {

    private ImageButton userBtn;
    private ImageButton searchBtn;
    private ImageButton curPlantsBtn;
    private ImageButton helpBtn;

    Calendar calendar;
    CalendarView calendarV;
    HashMap<String, ArrayList<Event>> eventMap = new HashMap<String, ArrayList<Event>>();

    ArrayList<Event> adapterList = new ArrayList<Event>();
    RecyclerView recyclerV;
    calendarAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    TextView plantsToWater;
    final String msgNoPlants = "No Plants to Water Today!";
    final String msgYesPlants = "Plants to Water Today:";

    int iterationsAhead = 4;//how many waters to create events for

    //  10.169.178.32
    String url = "http://10.169.178.32:8080/api/getUserByUsernameEmail/aidan/asloran23@gmail.com";

    String listID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_schedule);

        plantsToWater = (TextView)findViewById(R.id.waterTodayTextMsg);

        RequestQueue queue = SpringbootSingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("BEFORE LISTID ", "ovjodfvfovmv ksdvsdkvmc ");
                    listID = response.getString("listid");
                    Log.d("ID: ----- ", listID);
                } catch (JSONException e) {
                    Log.d("OJNFNJOSD error", "on response error");
                    throw new RuntimeException(e);
                }
            }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error){
                    plantsToWater.setText(error.toString());
                }
            });

        SpringbootSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        String[] plantsList = new String[8];
        String[] datesLastWatered = new String[8];
        String[] waterTimePeriod = new String[8];
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray n = response.getJSONArray("plantlist");
                    JSONArray wd = response.getJSONArray("lastWaterDates");
                    JSONArray wp = response.getJSONArray("plantperiods");
                    for(int i = 0; i < 8; i++){
                        plantsList[i] = n.getString(i);
                        datesLastWatered[i] = wd.getString(i);
                        waterTimePeriod[i] = wp.getString(i);

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){
                plantsToWater.setText(error.toString());
            }
        });

        SpringbootSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest2);

        // Calendar Functions //

        setContentView(R.layout.user_schedule);

        calendarV = (CalendarView)findViewById(R.id.calendar);
        calendar = Calendar.getInstance();

        calendar.setFirstDayOfWeek(1); //sets first day of week to sunday

        //get recycler view
        recyclerV = findViewById(R.id.idrecycle);
        //create adapter
        adapter = new calendarAdapter(this, adapterList);
        //create vertical list with linear layout manager
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //set layout manager and adapter to the recycler view
        recyclerV.setLayoutManager(linearLayoutManager);
        recyclerV.setAdapter(adapter);
        //new array


        //date of last water
        String[] dateLastWatered = new String[8];//in YYYY-MM-DD format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = sdf.format(calendar.getTime());
        Log.d("CUR_DATE", curDate);
        for(int i = 0; i < 8; i++){
            if(plantsList[i] != null){
                dateLastWatered[i] = curDate;
            }
        }

        ArrayList<Plant> mPlantList = new ArrayList<>();

        mPlantList.add(new Plant("Aloe", "Aloe harlana", "https:\\/\\/perenual.com\\/storage\\/species_image\\/721_aloe_harlana\\/regular\\/5582077315_8d613454aa_b.jpg", 721, "21"));
        mPlantList.add(new Plant("Lily of the Incas", "Alstroemeria isabellana", "https:\\/\\/perenual.com\\/storage\\/species_image\\/738_alstroemeria_isabellana\\/regular\\/29065273245_e56206d0d9_b.jpg", 738, "6-8"));
        mPlantList.add(new Plant("Hardy Kiwi", "Actinidia arguta", "https:\\/\\/perenual.com\\/storage\\/species_image\\/534_actinidia_arguta\\/regular\\/8054888032_690ac1e6ae_b.jpg", 534, "2-4"));
        mPlantList.add(new Plant("Lily of the Nile", "Agapanthus Blue Yonder", "https:\\/\\/perenual.com\\/storage\\/species_image\\/578_agapanthus_rfdd_double_diamond\\/regular\\/51685545730_a49c37d107_b.jpg", 573, "6-8"));


        //Event eTest = new Event("pls work", 2023, 11, 30);
        //Toast.makeText(getApplicationContext(), eTest.getDate(), Toast.LENGTH_SHORT).show();
        ArrayList<Event> pls = new ArrayList<Event>();
        /*for(int i = 0; i < 8; i++){
            if(plantsList[i] != null){
                int y = Integer.parseInt(dateLastWatered[i].substring(0, 4));
                int m = Integer.parseInt(dateLastWatered[i].substring(5, 7));
                int d = Integer.parseInt(dateLastWatered[i].substring(8));
                for(int n = 0; n < iterationsAhead; n++){
                    Event e = new Event(plantsList[i], y, m, d);
                    int freq = getWaterFrequencyInt(waterTimePeriod[i]);
                    e.setDate(y, m, d+(n*freq));
                    Log.d("PLEASE", e.getDate());
                    pls.add(e);
                }
            }
        }
        for(Plant p : mPlantList){
                if(p != null){
                for(int i = 0; i < iterationsAhead; i++){
        Event e = new Event(p);
        int waterFreq = getWaterFrequencyInt(p);
        int y = Integer.parseInt(curDate.substring(0, 4));
        int m = Integer.parseInt(curDate.substring(5, 7));
        int d = Integer.parseInt(curDate.substring(8)) + i*waterFreq;
        e.setDate(y, m, d);
        }
        Event e = new Event(p);
        pls.add(e);
        }
        }
        for(Event e : pls){
        String date = e.getDate();
        ArrayList<Event> eventList = eventMap.get(date);
        if(eventList == null){
        eventList = new ArrayList<Event>();
        eventList.add(e);
        eventMap.put(date, eventList);
        } else {
        eventList.add(e);
        }
        }
        int y = Integer.parseInt(curDate.substring(0, 4));
        int m = Integer.parseInt(curDate.substring(5, 7));
        int d = Integer.parseInt(curDate.substring(8));
        displayEvents(y, m, d);

        calendarV.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
@Override
// get the value of DAYS, MONTH, YEARS
public void onSelectedDayChange(@NonNull CalendarView calendarV, int year, int month, int day){
        String msg = "Selected date Month: " + (month+1)  + " Day : " + (day) + " Year " + (year);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        displayEvents(year, month+1, day);
        }
        });

        /*
        public void changeDateColor(Date date, int color){

        }




        // Navigates to User Page
        userBtn = (ImageButton) findViewById(R.id.profileButton);
        userBtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Intent intent = new Intent(CalendarActivity.this, UserPageActivity.class);
        startActivity(intent);
        finish();
        }
        });


        // Navigates to Search Plants Page
        searchBtn = (ImageButton) findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Intent intent = new Intent(CalendarActivity.this, SearchPageActivity.class);
        startActivity(intent);
        finish();
        }
        });


        // Navigates to Current Plants Page
        curPlantsBtn = (ImageButton) findViewById(R.id.plantButton);
        curPlantsBtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Intent intent = new Intent(CalendarActivity.this, CurrentPlantsActivity.class);
        startActivity(intent);
        finish();
        }
        });


        // Navigates to HowToUse Page
        helpBtn = (ImageButton) findViewById(R.id.helpButton);
        helpBtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Intent intent = new Intent(CalendarActivity.this, HowToUseActivity.class);
        startActivity(intent);
        finish();
        }
        });

        }

//calendar functions
public void setDate(int year, int month, int day){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        calendarV.setDate(milli);
        }

public void displayEvents(int year, int month, int day){
        String date = year + "-" + month + "-" + day;
        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
        ArrayList<Event> events = eventMap.get(date);
        if(events == null){
        events = new ArrayList<Event>();
        }
        if(events.isEmpty()){
        plantsToWater.setText(msgNoPlants);
        } else {
        plantsToWater.setText(msgYesPlants);
        }
        adapterList.clear();
        adapterList.addAll(events);
        adapter.notifyDataSetChanged();
        }

public int getWaterFrequencyInt(Plant p){//return water frequency for plant p in days
        String w = p.getWaterFrequency();
        int intFreq = 0;
        if(w.contains("-")){//water frequency is a range ex: 5-7
        int indexDash = w.indexOf("-");
        int bottom = Integer.parseInt(w.substring(0, indexDash));
        int top = Integer.parseInt(w.substring(indexDash+1));
        intFreq = (int)(bottom+top)/2;//average of the end points
        }  else {//single number
        intFreq = Integer.parseInt(w);
        }
        return intFreq;
        }

public int getWaterFrequencyInt(String w){//return water frequency for plant p in days
        int intFreq = 0;
        if(w.contains("-")){//water frequency is a range ex: 5-7
        int indexDash = w.indexOf("-");
        int bottom = Integer.parseInt(w.substring(0, indexDash));
        int top = Integer.parseInt(w.substring(indexDash+1));
        intFreq = (int)(bottom+top)/2;//average of the end points
        }  else {//single number
        intFreq = Integer.parseInt(w);
        }
        return intFreq;
        }

        }


        */