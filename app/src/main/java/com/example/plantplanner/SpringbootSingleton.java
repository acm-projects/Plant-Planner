package com.example.plantplanner;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/* This class represents a single instance of a connection to our springboot api,
   which allows us to reuse a connection and methods instead of rewriting them and
   reconnecting each time.

    //Get a RequestQueue
    RequestQueue queue = SpringbootSingleton.getInstance(this.getApplicationContext()).
    getRequestQueue();

    //instantiate your JSONObjectRequest
    //JSONObjectRequest jsonObjectRequest = new JSONObjectRequest (request type like get or post,
        string url so like "http://LeeVanCleef.com", new Response.Listener<JSONObject>() {},
        new Response.ErrorListener() {});

'http://10.122.178.180:8080/api/getPlantList/listid'
Request.method.GET, url, new Response.Listener<JSONObject>(
    //Add your request to RequestQueue
    SpringbootSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

 */
public class SpringbootSingleton {
    private static SpringbootSingleton instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context ctx;

    private SpringbootSingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

    }//constructor

    public synchronized static SpringbootSingleton getInstance(Context context) {
        if (instance == null)
            instance = new SpringbootSingleton(context);
        return instance;
    }//getInstance

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());

        return requestQueue;
    }//getRequestQueue

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

}//SpringbootSingleton
