package com.bowling.edward.bowling;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LocalAlleys2 extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private RecyclerView mListView;
    private static final int REQUEST_LOCATION = 0;
    private static final String API_KEY = "AIzaSyCIUdmvacCqSeWbOtLL1Tg2CEmYkMbBQew";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_DETAILS = "/details";
    private static final String TYPE_SEARCH = "/nearbysearch";
    private static final String OUT_JSON = "/json?";
    private static final String LOG_TAG = "ListRest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_alleys);
        mListView = findViewById(R.id.alleyView);
        getCoordinates();

    }
    public void getCoordinates(){
        if (ActivityCompat.checkSelfPermission(LocalAlleys2.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocalAlleys2.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LocalAlleys2.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else{
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Double longitude = location.getLongitude();
            Double latitude = location.getLatitude();
            String lng = Double.toString(longitude);
            String lat = Double.toString(latitude);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            int radius = 10000;
            List<Alley> list = search(longitude, latitude, radius);
            if (list != null) {
               // mListView = findViewById(R.id.alleyView);
                AlleyAdapter adapter = new AlleyAdapter(list);

                RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
                mListView.setLayoutManager(reLayoutManager);
                mListView.setItemAnimator(new DefaultItemAnimator());
                mListView.setAdapter(adapter);
                //mListView.setAdapter(adapter);
//                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list);
//                mListView.setAdapter(adapter);
            }
            else{
                Toast.makeText(LocalAlleys2.this, "No alleys nearby.", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public static ArrayList<Alley> search(double lat, double lng, int radius) {
        ArrayList<Alley> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_SEARCH);
            sb.append(OUT_JSON);
            sb.append("location=" + (lng) + "," + (lat));
            sb.append("&radius=" + (radius));
            sb.append("&type=bowling_alley");
            sb.append("&key=" + API_KEY);

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Alleys API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Alleys API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("results");
//            JSONArray geoJsonArray = predsJsonArray.getJSONArray(1);
//            JSONArray locJsonArray = geoJsonArray.getJSONArray(1);
//            JSONArray latJsonArray = locJsonArray.getJSONArray(1);
            resultList = new ArrayList<>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                Alley place = new Alley(null, null, 0);
                place.name = predsJsonArray.getJSONObject(i).getString("name");
                place.rating = predsJsonArray.getJSONObject(i).getDouble("rating");
                place.location = predsJsonArray.getJSONObject(i).getString("vicinity");
                //place.latitude = predsJsonArray.getJSONObject(i).getString("latitude");
//                place.longitude = predsJsonArray.getJSONObject(i).getString("longitude");
                resultList.add(place);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error processing JSON results", e);
        }

        return resultList;
    }

//    //Value Object for the ArrayList
//    public static class Alley {
//        private String reference;
//        private String name;
//        private double rating;
//        private String location;
//        public Alley(){
//            super();
//        }
//        @Override
//        public String toString(){
//            String name = this.name;
//            return "Name: " + name + "\n" + "Rating: " + rating + "\n" + "Location: " + location;
//        }
//    }

}


//import android.content.Intent;
//import android.os.StrictMode;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class LocalAlleys extends AppCompatActivity {
//
//    private RecyclerView mview;
//    private static final String api = "AIzaSyB6WVMPgbziFQU9XGti_tv_kP6noJSlXa0";
//
//    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
//
//    private static final String TYPE_SEARCH = "/nearbysearch";
//    private static final String OUT_JSON = "/json?";
//    private static final String LOG_TAG = "ListRest";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_local_alleys);
//        Intent intent = getIntent();
////        String longitude = intent.getStringExtra("long");
////        String latitude = intent.getStringExtra("lat");
//        String longitude = "-6.101934787829568";
//        String latitude ="53.204798049999994";
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        Double lng = Double.parseDouble(longitude);
//        Double lat = Double.parseDouble(latitude);
//        int radius = 100000;
//
//        ArrayList<Alley> list = search(lat, lng, radius);
//
//        if (list != null)
//        {
//            mview = findViewById(R.id.alleyView);
//            AlleyAdapter adapter = new AlleyAdapter(list);
//            mview.setAdapter(adapter);
//        }
//    }
//
//    public static ArrayList<Alley> search(double lat, double lng, int radius) {
//        ArrayList<Alley> resultList = null;
//
//        HttpURLConnection conn = null;
//        StringBuilder jsonResults = new StringBuilder();
//        try {
//            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
//            sb.append(TYPE_SEARCH);
//            sb.append(OUT_JSON);
//            sb.append("location=" + String.valueOf(lat) + "," + String.valueOf(lng));
//            sb.append("&radius=" + String.valueOf(radius));
//            sb.append("&type=BowlingAlley");
//            sb.append("&key=" + api);
//
//            URL url = new URL(sb.toString());
//            conn = (HttpURLConnection) url.openConnection();
//            InputStreamReader in = new InputStreamReader(conn.getInputStream());
//
//            int read;
//            char[] buff = new char[1024];
//            while ((read = in.read(buff)) != -1) {
//                jsonResults.append(buff, 0, read);
//            }
//        } catch (MalformedURLException e) {
//            Log.e(LOG_TAG, "Error processing Alleys API URL", e);
//            return resultList;
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "Error connecting to Alleys API", e);
//            return resultList;
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//        }
//
//        try {
//            // Create a JSON object hierarchy from the results
//            JSONObject jsonObj = new JSONObject(jsonResults.toString());
//            JSONArray predsJsonArray = jsonObj.getJSONArray("results");
//
//            // Extract the descriptions from the results
//            resultList = new ArrayList<>(predsJsonArray.length());
//            for (int i = 0; i < predsJsonArray.length(); i++) {
//                Alley alley = new Alley("Bray bowl" , null, null);
////                alley.location = predsJsonArray.getJSONObject(i).getString("location");
//                alley.name = predsJsonArray.getJSONObject(i).getString("name");
////                alley.email = predsJsonArray.getJSONObject(i).getString("email");
//                resultList.add(alley);
//            }
//        } catch (JSONException e) {
//            Log.e(LOG_TAG, "Error processing JSON results", e);
//        }
//
//        return resultList;
//    }
//
////    public static class Alley {
////        private String email;
////        private String name;
////        private String location;
////
////
////        public Alley(String name, String location, String email){
////            super();
////            this.name = name;
////            this.location = location;
////            this.email = email;
////        }
////
////    }
//}