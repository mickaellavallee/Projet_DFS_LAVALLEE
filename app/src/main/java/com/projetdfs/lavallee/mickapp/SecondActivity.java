package com.projetdfs.lavallee.mickapp;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class SecondActivity extends ListActivity {

    List<Second> mesSeconds = new ArrayList<Second>();
    Double distance;
    String media;
    String title;
    Double latitude;
    Double longitude;

    String TAG = "Parametres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jsonReceived = getIntent().getStringExtra("data");
        mesSeconds.clear();
        try{
            JSONObject jsonObjectReceived = new JSONObject(jsonReceived);
            Log.i(TAG,"Le JSON: "+jsonObjectReceived);
            for (int i=0;i<jsonObjectReceived.getJSONArray("data").length();i++){
                JSONArray data = jsonObjectReceived.getJSONArray("data");
                String type = data.getJSONObject(i).getString("type");
                String title = data.getJSONObject(i).getString("display");
                //Log.i("Type_data", type); test personnel
                String choice1 = "POI";
                String choice2 = "HOTEL";
                String choice3 = "RESTAURANT";
                String choice4 = "CITY";
                if (type.equals(choice1) ||
                        type.equals(choice2) ||
                        type.equals(choice3) ||
                        type.equals(choice4)) {
                    if (data.getJSONObject(i).has("distance")) {
                        distance = data.getJSONObject(i).getDouble("distance");
                        if (data.getJSONObject(i).has("media")) {

                            media = data.getJSONObject(i).getString("media");

                            if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("location")) {
                                latitude = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lat");
                                longitude = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lon");
                            } else latitude = 1.1;
                        } else {
                            media = "https://www.cocoland.info/coco_files/coco-marche-pas.png";
                            if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("location")) {
                                latitude = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lat");
                                longitude = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lon");
                            } else latitude = 1.1;
                        }
                        mesSeconds.add(new Second(media, type, title, distance, latitude, longitude));
                        Log.i(TAG, media + " . " + type + " . " + title + " . " + distance.toString());
                    }else{
                            if (data.getJSONObject(i).has("media")) {
                                media = data.getJSONObject(i).getString("media");
                                if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("location")){
                                    latitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lat");
                                    longitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lon");
                                }else latitude=1.1;
                            } else {
                                media = "https://www.cocoland.info/coco_files/coco-marche-pas.png";
                                if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("location")){
                                    latitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lat");
                                    longitude=jsonObjectReceived.getJSONArray("data").getJSONObject(i).getJSONObject("location").getJSONObject("coords").getDouble("lon");
                                }else latitude=1.1;
                            }
                            mesSeconds.add(new Second(media, type, title, 0.0,latitude,longitude));
                            Log.i(TAG, media + " . " + type + " . " + title + " . no distance available");
                        }
                    }
                }
            Log.i(TAG,mesSeconds.toString());
        }
        catch (Exception e){
            Log.e(TAG,e.toString());
        }
        ListView myListView = getListView();
        SecondActivityAdapter adapter = new SecondActivityAdapter(this, mesSeconds);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Second map = (Second) getListView().getItemAtPosition(position);
                switch (map.getType()){
                    case "POI":
                        Intent i = new Intent(SecondActivity.this,MapsActivity.class);
                        i.putExtra("latitude",latitude);
                        i.putExtra("longitude",longitude);
                        i.putExtra("title",title);
                        startActivity(i);
                        break;
                    case "CITY":
                        break;
                    case "HOTEL":
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://mlavallee.perso.ec-m.fr/"));
                        startActivity(intent);
                        break;
                }
            }
        });

    }
}